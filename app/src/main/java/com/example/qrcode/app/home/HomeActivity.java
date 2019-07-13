package com.example.qrcode.app.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.qrcode.R;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.HomeActivityBinding;

public class HomeActivity extends BaseActivity<HomeActivityBinding, HomeViewModel> {

    public HomeActivity(){
        super(HomeViewModel.class, R.layout.home_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
