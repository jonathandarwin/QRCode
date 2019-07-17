package com.example.qrcode.app.home.scan.transfer;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.qrcode.R;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.TransferActivityBinding;

public class TransferActivity extends BaseActivity<TransferActivityBinding, TransferViewModel> {

    public TransferActivity(){
        super(TransferViewModel.class, R.layout.transfer_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
