package com.example.qrcode.app.home;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import com.example.qrcode.R;
import com.example.qrcode.app.home.scan.ScanActivity;
import com.example.qrcode.app.home.showqr.ShowQrDialog;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.HomeActivityBinding;
import com.example.qrcode.model.User;
import com.example.qrcode.util.MoneyUtil;
import com.google.zxing.client.android.Intents;

public class HomeActivity extends BaseActivity<HomeActivityBinding, HomeViewModel>
            implements View.OnClickListener{

    public HomeActivity(){
        super(HomeViewModel.class, R.layout.home_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateDataUser();
        updateBalance();
    }

    @Override
    protected void setListener() {
        getBinding().menuScan.setOnClickListener(this);
        getBinding().menuShowQr.setOnClickListener(this);
        getBinding().menuId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().menuScan)){
            gotoIntent(ScanActivity.class, null, false);
        }
        else if(v.equals(getBinding().menuShowQr)){
            gotoIntent(ShowQrDialog.class, null, false);
        } else if (v.equals(getBinding().menuId)){

        }
    }

    private void updateDataUser(){
        getViewModel().updateUserData(loadUserData().getPhone()).observe(this, user -> {
            if(!user.equals(loadUserData())){
                saveUserData(user);
                updateBalance();
            }
        });
    }

    private void updateBalance(){
        getBinding().setBalance(MoneyUtil.convertMoney(loadUserData().getBalance()));
    }
}
