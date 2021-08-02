package com.cas.veritasapp.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by funmiayinde on 2019-09-09.
 */
public class ProgressUtil {

    private AlertDialog dialog;

    public void displayProgress(Context context) {
        if (dialog == null) {
//            dialog = new SpotsDialog(context, R.style.CustomDialog);
            dialog.setCancelable(false);
        }
        dialog.show();
    }

    public void closeProgress() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public AlertDialog getDialog() {
        return dialog;
    }

}
