package com.cas.veritasapp.util;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.cas.veritasapp.R;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.objects.api.ApiError;
import com.cas.veritasapp.objects.api.ApiResponse;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.HttpException;


/**
 * Created by funmiayinde on 2019-09-09.
 */
public class AppUtil {

    public static ApiError getError(Throwable throwable) {
        LogUtil.info("error >>>>>", throwable.getMessage());
        String errorString = throwable.getMessage();
        ApiError apiError = new ApiError();
        if (throwable instanceof HttpException) {
            ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
            ApiResponse apiResponse = new Gson().fromJson(responseBody.toString(), ApiResponse.class);
            errorString = apiResponse.get_meta().getError().getMessage();
            LogUtil.info("api response:", apiResponse.toString());
            apiError = apiResponse.get_meta().getError();
        } else if (throwable instanceof SocketTimeoutException) {
            errorString = "Server connection timed out";
        }
        apiError.setMessage(errorString);
        return apiError;
    }

    public static void logOut(Context context, Class aClass) {
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.apply();
        AppUtil.launchActivity(context, aClass, null, true);
    }
    public static void launchActivity(Context context, Class<?> cla, Bundle bundle, boolean isNewTask) {
        Intent intent = new Intent(context, cla);
        if (bundle != null)
            intent.putExtras(bundle);
        if (isNewTask)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public static boolean hasToken(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getString(AppConstant.TOKEN, null) != null;
    }

    public static String getToken(Context context) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getString(AppConstant.TOKEN, "");
    }

    public static Date stringToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



    @BindingAdapter("errorText")
    public static void setErrorMessage(TextInputLayout view, String message) {
        view.setError(message);
    }

    public static void updateToken(String token, Context context) {
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(AppConstant.TOKEN, token);
        editor.commit();
        LogUtil.write("token::::::" + token);
    }

    public static void loadFragment(AppCompatActivity activity, @IdRes int layout, Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void loadFragment(AppCompatActivity activity, @IdRes int layout, Fragment fragment, Bundle bundle) {
        // load fragment
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        transaction.replace(layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @BindingAdapter("selectedOption")
    public static void setSelected(MaterialSpinner spinner, String item) {
        LogUtil.write("setSelected option" + item);
        if (item != null && spinner.getItems() != null) {
//            int index = AppUtil.in
        }
    }

    public static String getFriendlyDate(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE - dd MMM, yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(calendar.getTime());
    }

    public static Calendar getSelectedDate(String date) {
        String[] value = date.split("-");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(value[0]);
        int month = Integer.parseInt(value[1]);
        int day = Integer.parseInt(value[2]);
        cal.set(Calendar.DATE, day);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.YEAR, year);
        return cal;
    }


    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static File saveImage(final Context context, final String imageData) {
        final byte[] imgBytesData = android.util.Base64.decode(imageData, android.util.Base64.DEFAULT);
        try {
            final File file = File.createTempFile("image", null, context.getCacheDir());
            final FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }

            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    fileOutputStream);
            try {
                bufferedOutputStream.write(imgBytesData);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
