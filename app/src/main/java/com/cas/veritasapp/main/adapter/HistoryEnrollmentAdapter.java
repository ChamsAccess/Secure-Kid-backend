package com.cas.veritasapp.main.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryEnrollmentAdapter extends RecyclerView.Adapter<HistoryEnrollmentAdapter.HistoryEnrollmentViewHolder> {
    private View viewRoot;
    private List<Enrollment> enrollmentList;
    private OnItemSelectedListener<Enrollment> listener;

    public HistoryEnrollmentAdapter(View viewRoot,
                                    ArrayList<Enrollment> enrollmentList,
                                    OnItemSelectedListener<Enrollment> listener) {
        this.viewRoot = viewRoot;
        this.enrollmentList = enrollmentList;
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void submitList(ArrayList<Enrollment> enrollmentList) {
        this.enrollmentList = enrollmentList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryEnrollmentAdapter.HistoryEnrollmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(viewRoot.getContext()).inflate(R.layout.list_enrollment_history_view, parent, false);
        return new HistoryEnrollmentAdapter.HistoryEnrollmentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HistoryEnrollmentViewHolder viewHolder, int position) {
        Enrollment enrollment = enrollmentList.get(position);
        if (enrollment != null) {
            if (enrollment.getPersonalObject() != null) {
                viewHolder.getFullNameTxtView().setText(
                        enrollment.getPersonalObject().getFirstName() + "-" + enrollment.getPersonalObject().getSurname());
                viewHolder.createdAtTxtView.setText(DateTimeUtils.formatWithPattern(enrollment.getCreateAt(), AppConstant.DATE_PATTERN));
                viewHolder.rsaPinStatus.setText((enrollment.getRsaPin() != null && !enrollment.getRsaPin().isEmpty())
                        ? enrollment.getRsaPin()
                        : "Pending");

                viewHolder.registrationStatusTextView.setText((enrollment.isSubmitted()) ? "Completed" : "Incomplete");
                viewHolder.editBtn.setOnClickListener(v -> listener.ontItemSelected(enrollment, AppConstant.ENROLLMENT));
//                if (enrollment.isSubmitted() && enrollment.getStatus().equals("Processing")) {
                    viewHolder.errorBtn.setOnClickListener(v -> listener.ontItemSelected(enrollment, AppConstant.ENROLLMENT_ERRORS));
//                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return enrollmentList.size();
    }


    static class HistoryEnrollmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fullNameTxtView)
        TextView fullNameTxtView;

        @BindView(R.id.createdAt)
        TextView createdAtTxtView;

        @BindView(R.id.registrationStatusTextView)
        TextView registrationStatusTextView;

        @BindView(R.id.rsaPinStatus)
        TextView rsaPinStatus;

        @BindView(R.id.editBtn)
        Button editBtn;

        @BindView(R.id.errorBtn)
        Button errorBtn;

        View view;

        public HistoryEnrollmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }


        public TextView getFullNameTxtView() {
            return fullNameTxtView;
        }

        public TextView getCreatedAtTxtView() {
            return createdAtTxtView;
        }

        public TextView getRegistrationStatusTextView() {
            return registrationStatusTextView;
        }

        public TextView getRsaPinStatus() {
            return rsaPinStatus;
        }

        public Button getEditBtn() {
            return editBtn;
        }

        public Button getErrorBtn() {
            return errorBtn;
        }

        public View getView() {
            return view;
        }
    }
}
