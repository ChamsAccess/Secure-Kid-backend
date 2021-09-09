package com.cas.veritasapp.main.home.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseDialogFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.databinding.FragmentSignatureBinding;
import com.cas.veritasapp.databinding.PreviewDialogBinding;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.payloads.EnrollmentPayload;
import com.cas.veritasapp.util.AppUtil;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class SignatureFragmentDialog extends BaseDialogFragment<FragmentSignatureBinding>
        implements View.OnClickListener, SignaturePad.OnSignedListener {

    @Inject
    EnrollmentViewModel viewModel;

    FragmentSignatureBinding binding;

    OnItemSelectedListener<Map> onItemSelectedListener;
    Map<String, Object> map = new HashMap<>();
    private String signatureKey;

    public SignatureFragmentDialog(OnItemSelectedListener onItemSelectedListener, String key) {
        super();
        this.onItemSelectedListener = onItemSelectedListener;
        this.signatureKey = key;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.setCancelable(false);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_signature;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        binding.setLifecycleOwner(this);
        this.initialize();
    }

    private void initialize() {
        binding.clearSignatureButton.setOnClickListener(this);
        binding.saveSigntureButton.setOnClickListener(this);
        binding.signaturePad.setOnSignedListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clearSignatureButton:
                binding.signaturePad.clear();
                break;
            case R.id.close:
                dismiss();
                break;
            case R.id.saveSigntureButton:
                Bitmap signatureBitmap = binding.signaturePad.getSignatureBitmap();
                if (addJpgSignatureToGallery(signatureBitmap)) {
                    showToast("Signature saved successfully");
                    onItemSelectedListener.ontItemSelected(map, signatureKey);
                    dismiss();
                } else {
                    showToast("Unable to save the signature");
                }
                break;
        }
    }

    @Override
    public void onStartSigning() {

    }

    @Override
    public void onSigned() {
        binding.saveSigntureButton.setEnabled(true);
        binding.clearSignatureButton.setEnabled(true);
    }

    @Override
    public void onClear() {
        binding.saveSigntureButton.setEnabled(false);
        binding.clearSignatureButton.setEnabled(false);
    }

    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    private void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    private boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            map.put("file", photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        requireActivity().sendBroadcast(mediaScanIntent);
    }

    private boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
