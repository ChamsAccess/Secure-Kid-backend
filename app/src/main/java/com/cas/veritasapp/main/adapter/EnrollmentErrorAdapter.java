package com.cas.veritasapp.main.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.data.entities.Enrollment;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnrollmentErrorAdapter extends RecyclerView.Adapter<EnrollmentErrorAdapter.EnrollmentErrorViewHolder> {
    private View viewRoot;
    private List<String> enrollmentErrorList;

    public EnrollmentErrorAdapter(View viewRoot,
                                  ArrayList<String> enrollmentErrorList) {
        this.viewRoot = viewRoot;
        this.enrollmentErrorList = enrollmentErrorList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void submitList(ArrayList<String> enrollmentList) {
        this.enrollmentErrorList = enrollmentList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EnrollmentErrorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(viewRoot.getContext()).inflate(R.layout.list_enrollment_error_view, parent, false);
        return new EnrollmentErrorViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EnrollmentErrorViewHolder viewHolder, int position) {
        String error = enrollmentErrorList.get(position);
        if (error != null) {
            viewHolder.getError().setText(error);
        } else {
            viewHolder.getError().setText("No error for this enrollment.");
        }
    }

    @Override
    public int getItemCount() {
        return enrollmentErrorList.size();
    }


    static class EnrollmentErrorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.errorTxtView)
        TextView error;

        View view;

        public EnrollmentErrorViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public TextView getError() {
            return error;
        }

        public View getView() {
            return view;
        }
    }
}
