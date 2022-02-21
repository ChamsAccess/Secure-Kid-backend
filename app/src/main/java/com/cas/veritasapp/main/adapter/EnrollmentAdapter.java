package com.cas.veritasapp.main.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.data.entities.Enrollment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnrollmentAdapter extends RecyclerView.Adapter<EnrollmentAdapter.EnrollmentViewHolder> {
    private View viewRoot;
    private List<Enrollment> enrollmentList;

    public EnrollmentAdapter(View viewRoot, ArrayList<Enrollment> enrollmentList) {
        this.viewRoot = viewRoot;
        this.enrollmentList = enrollmentList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void submitList(ArrayList<Enrollment> enrollmentList){
        this.enrollmentList = enrollmentList;
        this.notifyDataSetChanged();
    }

    @Override
    public EnrollmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(viewRoot.getContext()).inflate(R.layout.enrollment_history, parent, false);
        return new EnrollmentViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return enrollmentList.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(EnrollmentViewHolder holder, int position) {
        if (holder instanceof EnrollmentViewHolder) {
            EnrollmentViewHolder viewHolder = (EnrollmentViewHolder) holder;
            Enrollment enrollment = enrollmentList.get(position);
            if (enrollment != null){
                if (enrollment.getPersonalObject() != null){
                    viewHolder.getFullNameTxtView().setText(
                            enrollment.getPersonalObject().getFirstName() + "-" + enrollment.getPersonalObject().getSurname());
                }
                viewHolder.getRsPinTxtView().setText((enrollment.gettPin() != null && !enrollment.gettPin().isEmpty())
                        ? enrollment.gettPin()
                        : "Pending");
            }
        }
    }

    static class EnrollmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.userImageView)
        ImageView userImageView;

        @BindView(R.id.fullNameTxtView)
        TextView fullNameTxtView;

        @BindView(R.id.rsPinTxtView)
        TextView rsPinTxtView;

        View view;

        public EnrollmentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public ImageView getUserImageView() {
            return userImageView;
        }

        public TextView getFullNameTxtView() {
            return fullNameTxtView;
        }

        public TextView getRsPinTxtView() {
            return rsPinTxtView;
        }

        public View getView() {
            return view;
        }
    }
}
