package com.example.qrcode.app.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.qrcode.R;
import com.example.qrcode.app.home.HomeActivity;
import com.example.qrcode.app.register.RegisterActivity;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.LoginActivityBinding;
import com.example.qrcode.model.User;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity<LoginActivityBinding, LoginViewModel>
            implements View.OnClickListener{

    public LoginActivity(){
        super(LoginViewModel.class, R.layout.login_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isLoggedIn()){
            gotoHomeActivity();
        }
        getBinding().setViewModel(new User());
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
            User user = getBinding().getViewModel();
            if(getViewModel().validateInput(user)){
                getViewModel().doLogin(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(success -> {
                            if(success){
                                gotoHomeActivity();
                            }
                            else{
                                showToast(getResources().getString(R.string.text_error));
                            }
                        }, error -> {
                            showToast(getResources().getString(R.string.text_error));
                        });
            }
            else{
                showToast(getResources().getString(R.string.text_error_register_input_all));
            }
        }
        else if (v.equals(getBinding().tvSignUp)){
            gotoIntent(RegisterActivity.class, null, false);
        }
    }

    private void gotoHomeActivity(){
        gotoIntent(HomeActivity.class, null, true);
    }
}
