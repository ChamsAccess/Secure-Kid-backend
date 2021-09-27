package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.databinding.FragmentPfaCertBinding;
import com.cas.veritasapp.main.home.dialog.PreviewFragmentDialog;
import com.cas.veritasapp.main.home.dialog.SignatureFragmentDialog;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.Media;
import com.cas.veritasapp.objects.PFACertification;
import com.cas.veritasapp.objects.api.ApiError;
import com.mikhaellopez.lazydatepicker.LazyDatePicker;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PFACertificationFragment extends BaseFragment<FragmentPfaCertBinding>
        implements View.OnClickListener,
        LazyDatePicker.OnDatePickListener, LazyDatePicker.OnDateSelectedListener,
        OnItemSelectedListener<Map> {

    FragmentPfaCertBinding binding;

    @Inject
    EnrollmentViewModel viewModel;

    PFACertification certification;

    private String fileType;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pfa_cert;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
        binding.setLifecycleOwner(this);

        initApp();
    }

    private void initApp() {
        if (viewModel.getCurrent() != null && viewModel.getCurrent().getPfa_certificationObject() != null) {
            PFACertification pfaCertification = viewModel.getCurrent().getPfa_certificationObject();
            if (pfaCertification != null) {
                Media agentSignatureMedia = pfaCertification.getSignature();
                Picasso.get()
                        .load((agentSignatureMedia != null && !agentSignatureMedia.file.url.isEmpty())
                                ? agentSignatureMedia.file.url : null)
                        .placeholder(R.drawable.ic_signature)
                        .into(binding.agentSignatureImageView);
            }
        }
        certification = new PFACertification();

        binding.agentSignatureImageView.setOnClickListener(this);
        binding.saveBtn.setOnClickListener(this);
    }

    private void uploadFile(File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        viewModel.uploadFile(body, AppConstant.CAPTURE_AGENT_SIGNATURE_ACTION).observe(getViewLifecycleOwner(), this::performAction);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    String pictureUrl = data.getStringExtra("pictureUrl");
                    File file = new File(pictureUrl);
                    Picasso.get()
                            .load(file)
                            .placeholder(R.drawable.ic_signature)
                            .into(binding.agentSignatureImageView);
                    uploadFile(file);
                    viewModel.getCurrent().setPfa_certificationObject(certification);
                    break;
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBtn:
                viewModel.getCurrent().setPfa_certificationObject(certification);
                PreviewFragmentDialog dialog = new PreviewFragmentDialog(viewModel.getCurrent(),
                        (viewModel.getCurrent() != null && viewModel.getCurrent().get_id() == null)
                                ? AppConstant.CREATE_ENROLLMENT : AppConstant.UPDATE_ENROLLMENT);
                dialog.show(requireActivity().getSupportFragmentManager(), "Preview Data");
                break;
            case R.id.agentSignatureImageView:
                SignatureFragmentDialog signatureFragmentDialog = new SignatureFragmentDialog(this, AppConstant.SAVE_AGENT_SIGNATURE);
                signatureFragmentDialog.show(requireActivity().getSupportFragmentManager(), "Preview Data");
                break;
        }
    }


    @Override
    public void onLoad(String key) {
        super.onLoad(key);
        if (key.equals(AppConstant.CAPTURE_AGENT_SIGNATURE_ACTION)) {
            showProgressDialog();
        }
    }

    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
        switch (key) {
            case AppConstant.CAPTURE_AGENT_SIGNATURE_ACTION:
                if (obj instanceof Media) {
                    Media media = (Media) obj;
                    certification.setSignature(media);
                    viewModel.getCurrent().setPfa_certificationObject(certification);
                }
                break;
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
        if (key.equals(AppConstant.CAPTURE_AGENT_SIGNATURE_ACTION)) {
            hideProgressDialog();
        }
    }

    @Override
    public void onDatePick(Date dateSelected) {
        binding.enrolledDate.setDate(dateSelected);
        certification.setEnrolled_dated(LazyDatePicker.dateToString(dateSelected, DATE_FORMAT));
        viewModel.getCurrent().setPfa_certificationObject(certification);
    }

    @Override
    public void onDateSelected(Boolean dateSelected) {

    }

    @Override
    public void ontItemSelected(Map map, String key) {
        if (map != null && key.equals(AppConstant.SAVE_AGENT_SIGNATURE)) {
            File file = (File) map.get("file");
            if (file != null) {
                Picasso.get()
                        .load(file)
                        .placeholder(R.drawable.ic_passport)
                        .into(binding.agentSignatureImageView);
                uploadFile(file);
            }
        }
    }
}
