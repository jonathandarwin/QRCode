package com.example.qrcode.app.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.qrcode.R;
import com.example.qrcode.app.register.RegisterActivity;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.LoginActivityBinding;

public class LoginActivity extends BaseActivity<LoginActivityBinding, LoginViewModel>
            implements View.OnClickListener{

    public LoginActivity(){
        super(LoginViewModel.class, R.layout.login_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setListener() {
        super.setListener();
        getBinding().btnLogin.setOnClickListener(this);
        getBinding().tvSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnLogin)){

        }
        else if (v.equals(getBinding().tvSignUp)){
            gotoIntent(RegisterActivity.class, null, false);
        }
    }
}
