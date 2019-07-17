package com.example.qrcode.app.home.scan.transfer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.qrcode.R;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.TransferActivityBinding;
import com.example.qrcode.util.MoneyUtil;

public class TransferActivity extends BaseActivity<TransferActivityBinding, TransferViewModel>
            implements View.OnClickListener{

    public TransferActivity(){
        super(TransferViewModel.class, R.layout.transfer_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setUpdatePhone(true);
        getBinding().setPhone(getIntent().getExtras().getString("phone"));
        getBinding().setPrice("");
        getBinding().setBalance(MoneyUtil.convertMoney(loadUserData().getBalance()));
    }

    @Override
    protected void setListener() {
        getBinding().back.setOnClickListener(this);
        getBinding().btnTransfer.setOnClickListener(this);
        getBinding().txtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String money = MoneyUtil.removeDotMoney(s.toString());
                String newMoney = MoneyUtil.convertMoney(money);
                if(getViewModel().getUpdatePhone()){
                    getViewModel().setUpdatePhone(false);
                    getBinding().txtPrice.setText(newMoney);
                    getBinding().txtPrice.setSelection(newMoney.length());
                }
                else{
                    getViewModel().setUpdatePhone(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().back)){
            finish();
        }
        else if (v.equals(getBinding().btnTransfer)){

        }
    }
}
