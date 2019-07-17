package com.example.qrcode.app.home.scan.transfer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.qrcode.R;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.TransferActivityBinding;

public class TransferActivity extends BaseActivity<TransferActivityBinding, TransferViewModel>
            implements View.OnClickListener{

    public TransferActivity(){
        super(TransferViewModel.class, R.layout.transfer_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().setPhone(getIntent().getExtras().getString("phone"));
        getBinding().setPrice("");
    }

    @Override
    protected void setListener() {
        getBinding().back.setOnClickListener(this);
        getBinding().btnTransfer.setOnClickListener(this);
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
