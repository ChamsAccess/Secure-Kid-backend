package com.cas.veritasapp.main;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;

import com.cas.veritasapp.R;;
import com.cas.veritasapp.core.base.BaseActivity;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.databinding.ActivityEnterBinding;
import com.cas.veritasapp.main.auth.AuthActivity;
import com.cas.veritasapp.main.home.HomeActivity;
import com.cas.veritasapp.util.AppUtil;
import com.cas.veritasapp.util.PrefUtil;


/**
 * Created by funmiayinde on 2019-10-20.
 */
public class EntryActivity extends BaseActivity<ActivityEnterBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_enter;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        CountDownTimer countDown = new CountDownTimer(2000, 1000) {
            @Override
            public void onFinish() {
                initApp();
            }

            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
            }
        };
        countDown.start();
    }


    private void initApp() {
        if (!AppUtil.hasToken(context)) {
            launchActivity(EntryActivity.this, AuthActivity.class);
        } else {
            launchActivity(EntryActivity.this, HomeActivity.class);
        }
    }
}
