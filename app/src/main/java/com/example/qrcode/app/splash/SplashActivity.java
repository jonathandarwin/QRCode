package com.example.qrcode.app.splash;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.example.qrcode.R;
import com.example.qrcode.app.home.HomeActivity;
import com.example.qrcode.app.started.StartedActivity;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.SplashActivityBinding;

public class SplashActivity extends BaseActivity<SplashActivityBinding, SplashViewModel> {

    public SplashActivity(){
        super(SplashViewModel.class, R.layout.splash_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isLoggedIn()){
                    gotoIntent(HomeActivity.class, null, true);
                }
                else{
                    gotoIntent(StartedActivity.class, null, true);
                }
            }
        }, 2000);
    }
}
