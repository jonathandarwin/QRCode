package com.example.qrcode.app.home.scan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.qrcode.R;
import com.example.qrcode.app.home.scan.transfer.TransferActivity;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.ScanActivityBinding;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends BaseActivity<ScanActivityBinding, ScanViewModel> {

    ZXingScannerView zXingScannerView;

    public ScanActivity(){
        super(ScanViewModel.class, R.layout.scan_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(result ->{
            Bundle bundle = new Bundle();
            bundle.putString("phone", result.getText());
            gotoIntent(TransferActivity.class, bundle, true);
        });
        zXingScannerView.startCamera();
    }
}
