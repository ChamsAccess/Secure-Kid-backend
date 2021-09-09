package com.cas.veritasapp.main.home.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseDialogFragment;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.databinding.PreviewDialogBinding;
import com.cas.veritasapp.main.home.rvvm.enrollment.EnrollmentViewModel;
import com.cas.veritasapp.objects.ContributionBio;
import com.cas.veritasapp.objects.Enrollment;
import com.cas.veritasapp.objects.Media;
import com.cas.veritasapp.objects.PFACertification;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.payloads.EnrollmentPayload;
import com.cas.veritasapp.util.AppUtil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class PreviewFragmentDialog extends BaseDialogFragment<PreviewDialogBinding>
        implements View.OnClickListener {

    @Inject
    EnrollmentViewModel viewModel;

    PreviewDialogBinding binding;
    private final Enrollment enrollment;

    public PreviewFragmentDialog(Enrollment enrollment) {
        super();
        this.enrollment = enrollment;
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
        return R.layout.preview_dialog;
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
        binding.setEnrollment(enrollment);
        binding.close.setOnClickListener(this);


        if (enrollment != null) {
            ContributionBio contributionBio = enrollment.getContributionBioObject();
            PFACertification pfaCertification = enrollment.getPfa_certificationObject();
            if (contributionBio != null) {
                Media userPhotoMedia = contributionBio.getPassport();
                Media userSignatureMedia = contributionBio.getSignature();
                Picasso.get()
                        .load((userPhotoMedia != null && !userPhotoMedia.file.url.isEmpty())
                                ? userPhotoMedia.file.url : null)
                        .placeholder(R.drawable.ic_passport)
                        .into(binding.passportImageView);

                Picasso.get()
                        .load((userSignatureMedia != null && !userSignatureMedia.file.url.isEmpty())
                                ? userSignatureMedia.file.url : null)
                        .placeholder(R.drawable.ic_passport)
                        .into(binding.userSignatureImageView);
            }
            if (pfaCertification != null) {
                Media agentSignatureMedia = pfaCertification.getSignature();
                Picasso.get()
                        .load((agentSignatureMedia != null && !agentSignatureMedia.file.url.isEmpty())
                                ? agentSignatureMedia.file.url : null)
                        .placeholder(R.drawable.ic_signature)
                        .into(binding.agentSignatureImageView);
            }
        }


        binding.saveBtn.setOnClickListener(v -> {
            if (enrollment != null) {
                Map<String, Object> request = new HashMap<>();
                request.put("personal", enrollment.getPersonalObject());
                request.put("next_of_kin", enrollment.getNextOfKinObject());
                request.put("employment", enrollment.getEmploymentObject());
                request.put("salary", enrollment.getSalaryObject());
                request.put("pfa_certification", enrollment.getPfa_certificationObject());
                request.put("contribution_bio", enrollment.getContributionBioObject());
                if (enrollment.get_id() != null) {
                    viewModel.senNewEnrollment(request).observe(getViewLifecycleOwner(), this::performAction);
                } else {
                    viewModel.updateEnrollment(request).observe(getViewLifecycleOwner(), this::performAction);
                }
            }
            hideProgressDialog();
            showToast("Enrollment was successful");
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                dismiss();
                break;
        }
    }

    @Override
    public void onLoad(String key) {
        super.onLoad(key);
        if (AppConstant.CREATE_ENROLLMENT.equals(key)) {
            dismiss();
        }
        hideProgressDialog();
    }

    @Override
    public void onSuccess(Object obj, String key) {
        super.onSuccess(obj, key);
        hideProgressDialog();
        if (AppConstant.CREATE_ENROLLMENT.equals(key)) {
            if (obj instanceof EnrollmentPayload) {
                EnrollmentPayload payload = (EnrollmentPayload) obj;
                if (payload != null) {
                    showToast("Enrollment was successful");
                }
            }
        }
    }

    @Override
    public void onError(ApiError apiError, String key) {
        super.onError(apiError, key);
        if (AppConstant.CREATE_ENROLLMENT.equals(key)) {
            showToast("An error occurred while processing enrollment");
        }
        hideProgressDialog();
        showToast(apiError.getMessage());
    }
}
