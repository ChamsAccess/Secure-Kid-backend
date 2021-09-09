package com.cas.veritasapp.main.home.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.databinding.FragmentContributionBioCertBinding;
import com.cas.veritasapp.main.home.dialog.SignatureFragmentDialog;
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
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ContributionBioInfoFragment extends BaseFragment<FragmentContributionBioCertBinding>
        implements View.OnClickListener,
        LazyDatePicker.OnDatePickListener, LazyDatePicker.OnDateSelectedListener,
        OnItemSelectedListener<Map> {

    FragmentContributionBioCertBinding binding;
    private String pictureUrl;
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

    private void processFile() {
        Enrollment enrollment = enrollmentViewModel.getCurrent();
        if (enrollment != null) {
            String photo = enrollment.getPhoto();
            String signature = enrollment.getSignature();
            if (photo != null && !photo.isEmpty()) {
                File photoFile = AppUtil.saveImage(requireActivity(), photo);
                uploadFile(photoFile, AppConstant.CAPTURE_USER_IMAGE_ACTION);
                byte[] photoBytes = Base64.decode(photo.getBytes(), Base64.DEFAULT);
                binding.passportImageView.setImageBitmap(BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length));
            }
            if (signature != null && !signature.isEmpty()) {
                File signatureFile = AppUtil.saveImage(requireActivity(), signature);
                uploadFile(signatureFile, AppConstant.CAPTURE_USER_SIGNATURE_ACTION);
                byte[] signatureBytes = Base64.decode(signature.getBytes(), Base64.DEFAULT);
                binding.signatureImageView.setImageBitmap(BitmapFactory.decodeByteArray(signatureBytes, 0, signatureBytes.length));
            }
//            enrollment.setContributionBioObject(contributionBio);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    pictureUrl = data.getStringExtra("pictureUrl");
                    File file = new File(pictureUrl);
                    if (file != null) {
                        Picasso.get()
                                .load(file)
                                .placeholder(R.mipmap.ic_launcher)
                                .into(binding.passportImageView);
                        uploadFile(file, AppConstant.CAPTURE_USER_IMAGE_ACTION);
                    }
                    break;
            }
        }
    }

    private void uploadFile(File file, String key) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        enrollmentViewModel.uploadFile(body, key).observe(getViewLifecycleOwner(), this::performAction);
    }

    @Override
    public void onLoad(String key) {
        super.onLoad(key);
        if (key.equals(AppConstant.CAPTURE_USER_SIGNATURE_ACTION) ||
                key.equals(AppConstant.CAPTURE_USER_IMAGE_ACTION)) {
            showProgressDialog();
        }

    }

    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
        switch (key) {
            case AppConstant.CAPTURE_USER_SIGNATURE_ACTION:
                if (obj instanceof Media) {
                    Media media = (Media) obj;
                    contributionBio.setSignature(media);
                    enrollmentViewModel.getCurrent().setPhoto(media.file.url);
                    enrollmentViewModel.getCurrent().setContributionBioObject(contributionBio);
                }
                break;
            case AppConstant.CAPTURE_USER_IMAGE_ACTION:
                if (obj instanceof Media) {
                    Media media = (Media) obj;
                    contributionBio.setPassport(media);
                    enrollmentViewModel.getCurrent().setSignature(media.file.url);
                    enrollmentViewModel.getCurrent().setContributionBioObject(contributionBio);
                }
                break;
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
        if (key.equals(AppConstant.CAPTURE_USER_SIGNATURE_ACTION) ||
                key.equals(AppConstant.CAPTURE_USER_IMAGE_ACTION)) {
            showProgressDialog();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signatureImageView:
                SignatureFragmentDialog dialog = new SignatureFragmentDialog(this, AppConstant.SAVE_USER_SIGNATURE);
                dialog.show(requireActivity().getSupportFragmentManager(), "Preview Data");
                break;
            case R.id.passportImageView:
                startPictureActivity();
                break;
            case R.id.saveBtn:
                showToast("Contribution Bio info saved successfully");
                enrollmentViewModel.getCurrent().setContributionBioObject(contributionBio);
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

    @Override
    public void ontItemSelected(Map map, String key) {
        if (map != null && key.equals(AppConstant.SAVE_USER_SIGNATURE)) {
            File file = (File) map.get("file");
            if (file != null) {
                Picasso.get()
                        .load(file)
                        .placeholder(R.drawable.ic_passport)
                        .into(binding.signatureImageView);
                uploadFile(file, AppConstant.CAPTURE_USER_SIGNATURE_ACTION);
            }
        }
    }
}
