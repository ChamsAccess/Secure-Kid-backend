package com.cas.veritasapp.main.home.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.base.BaseFragment;
import com.cas.veritasapp.databinding.FragmentPersonalDataBinding;

import org.jetbrains.annotations.NotNull;

import dagger.android.support.AndroidSupportInjection;

public class PersonalDataFragment extends BaseFragment<FragmentPersonalDataBinding>
        implements View.OnClickListener {

    FragmentPersonalDataBinding binding;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_personal_data;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            showDialog();
        }
    }


    public void showDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(true);
        LayoutInflater inflater = getLayoutInflater();
        View convertView = inflater.inflate(R.layout.nin_search, null);
        EditText ninEditText = convertView.findViewById(R.id.ninEdt);
        Button searchNINBtn = convertView.findViewById(R.id.searchNinButton);
        Button skipBtn = convertView.findViewById(R.id.skipButton);
        dialog.setView(convertView);

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
        skipBtn.setOnClickListener(v -> alertDialog.dismiss());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
