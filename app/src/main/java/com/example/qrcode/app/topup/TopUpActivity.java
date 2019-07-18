package com.example.qrcode.app.topup;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.qrcode.R;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.TopupActivityBinding;

public class TopUpActivity extends BaseActivity<TopupActivityBinding, TopUpViewModel> {

    public TopUpActivity(){
        super(TopUpViewModel.class, R.layout.topup_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
