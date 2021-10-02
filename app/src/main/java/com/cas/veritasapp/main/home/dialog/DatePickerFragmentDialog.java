package com.cas.veritasapp.main.home.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.core.listeners.OnItemSelectedListener;
import com.cas.veritasapp.util.LogUtil;

import java.util.Calendar;

public class DatePickerFragmentDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private OnItemSelectedListener listener;
    private String currentDate;
    private String key;

    public DatePickerFragmentDialog(OnItemSelectedListener onItemSelectedListener, String key){
        this.listener = onItemSelectedListener;
        this.key = key;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Bundle bundle = getArguments();
        currentDate = bundle.getString((key != null && !key.isEmpty()) ? key : AppConstant.DATE);
        if (currentDate != null && !TextUtils.isEmpty(currentDate)) {
            String[] date = currentDate.split("-");
            day = Integer.parseInt(date[2]);
            month = Integer.parseInt(date[1]) - 1;
            year = Integer.parseInt(date[0]);
        }
        LogUtil.write("CurrentDate >>>>>> " + currentDate);
        return new DatePickerDialog(requireActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.currentDate = String.format("%s-%02d-%02d", year, month+ 1, dayOfMonth);
        listener.ontItemSelected(this.currentDate, AppConstant.DATE);
    }
}
