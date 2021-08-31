package com.cas.veritasapp.main.home.fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.camerakit.CameraKitView;
import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.databinding.FragmentContributionBioCertBinding;
import com.cas.veritasapp.databinding.FragmentNewEnrollmentBinding;
import com.cas.veritasapp.main.PictureActivity;
import com.cas.veritasapp.main.PictureActivity2;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.ContributionBio;
import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.Media;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.util.AppUtil;
import com.mikhaellopez.lazydatepicker.LazyDatePicker;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ContributionBioInfoFragment extends BaseFragment<FragmentContributionBioCertBinding>
        implements View.OnClickListener, LazyDatePicker.OnDatePickListener, LazyDatePicker.OnDateSelectedListener {

    FragmentContributionBioCertBinding binding;
    private String pictureUrl;
    private String fileType;
    private ContributionBio contributionBio;

    @Inject
    EnrollmentViewModel enrollmentViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_contribution_bio_cert;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        binding.setViewModel(enrollmentViewModel);
        binding.executePendingBindings();
        binding.setLifecycleOwner(this);

        initApp();

    }

    private void initApp() {
        contributionBio = new ContributionBio();

        binding.passportImageView.setOnClickListener(this);
        binding.signatureImageView.setOnClickListener(this);
        binding.saveBtn.setOnClickListener(this);

        processFile();

    }

    private void processFile(){
        Enrollment enrollment = enrollmentViewModel.getCurrent();
        if (enrollment != null) {
            String photo = enrollment.getPhoto();
            String signature = enrollment.getSignature();
            if (photo != null && !photo.isEmpty()) {
                fileType = AppConstant.PASSPORT;
                File photoFile = AppUtil.saveImage(requireActivity(), photo);
                uploadFile(photoFile);
                byte[] photoBytes = Base64.decode(photo.getBytes(), Base64.DEFAULT);
                binding.passportImageView.setImageBitmap(BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length));
            }
            if (signature != null && !signature.isEmpty()) {
                fileType = AppConstant.SIGNATURE;
                File signatureFile = AppUtil.saveImage(requireActivity(), signature);
                uploadFile(signatureFile);
                byte[] signatureBytes = Base64.decode(signature.getBytes(), Base64.DEFAULT);
                binding.signatureImageView.setImageBitmap(BitmapFactory.decodeByteArray(signatureBytes, 0, signatureBytes.length));
            }
            enrollment.setContributionBioObject(contributionBio);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    pictureUrl = data.getStringExtra("pictureUrl");
                    File file = new File(pictureUrl);
                    contributionBio.setPassportUrl(pictureUrl);
                    if (fileType.equals(AppConstant.PASSPORT)) {
                        Picasso.get()
                                .load(file)
                                .placeholder(R.mipmap.ic_launcher)
                                .into(binding.passportImageView);

                    } else if (fileType.equals(AppConstant.SIGNATURE)) {
                        contributionBio.setSignatureUrl(pictureUrl);
                        Picasso.get()
                                .load(file)
                                .placeholder(R.mipmap.ic_launcher)
                                .into(binding.signatureImageView);
                    }
                    uploadFile(file);
                    break;
            }
        }
    }

    private void uploadFile(File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        enrollmentViewModel.uploadFile(body).observe(getViewLifecycleOwner(), this::performAction);
    }

    @Override
    public void onLoad(String key) {
        super.onLoad(key);
        if (key.equals(AppConstant.UPLOAD_ACTION)) {
            showProgressDialog();
        }
    }

    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
        switch (key) {
            case AppConstant.UPLOAD_ACTION:
                if (obj instanceof Media) {
                    Media media = (Media) obj;
                    if (fileType.equals(AppConstant.PASSPORT)) {
                        contributionBio.setPassport(media._id);
                    } else {
                        contributionBio.setSignature(media._id);
                    }
                    enrollmentViewModel.getCurrent().setContributionBioObject(contributionBio);
                }
                break;
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
        if (key.equals(AppConstant.UPLOAD_ACTION)) {
            hideProgressDialog();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signatureImageView:
                fileType = AppConstant.SIGNATURE;
                startPictureActivity();
                break;
            case R.id.passportImageView:
                fileType = AppConstant.PASSPORT;
                startPictureActivity();
                break;
                case R.id.saveBtn:
                    showToast("Contribution Bio info saved successfully");
                break;
        }
    }

    @Override
    public void onDatePick(Date dateSelected) {
        binding.contributedDate.setDate(dateSelected);
        contributionBio.setSignature_date(LazyDatePicker.dateToString(dateSelected, DATE_FORMAT));
        enrollmentViewModel.getCurrent().setContributionBioObject(contributionBio);
    }

    @Override
    public void onDateSelected(Boolean dateSelected) {

    }
}
