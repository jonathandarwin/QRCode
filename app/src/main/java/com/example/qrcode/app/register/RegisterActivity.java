package com.example.qrcode.app.register;

import android.media.DrmInitData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.example.qrcode.R;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.RegisterActivityBinding;
import com.example.qrcode.model.User;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterActivity extends BaseActivity<RegisterActivityBinding, RegisterViewModel>
            implements View.OnClickListener{

    public RegisterActivity(){
        super(RegisterViewModel.class, R.layout.register_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().setViewModel(new User());
    }

    @Override
    protected void setListener() {
        super.setListener();
        getBinding().btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().btnSignUp)){
            User user = getBinding().getViewModel();
            if(getViewModel().validateInput(user)){
                getViewModel().getUserByEmail(user.getEmail())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(isValid -> {
                            if(isValid){
                                insertUser(user);
                            }
                            else{
                                showToast(getResources().getString(R.string.text_error_register_exists));
                            }
                        }, error -> {
                            showToast(getResources().getString(R.string.text_error));
                        });
            }
            else{
                showToast(getResources().getString(R.string.text_error_register_input_all));
            }
        }
    }

    private void insertUser(User user){
        if(getViewModel().insertUser(user)){
            showToast(getResources().getString(R.string.text_success));
            finish();
        }
        else{
            showToast(getResources().getString(R.string.text_error));
        }
    }
}
