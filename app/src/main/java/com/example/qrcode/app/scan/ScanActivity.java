package com.example.qrcode.app.scan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.qrcode.R;
import com.example.qrcode.app.transfer.TransferActivity;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.ScanActivityBinding;
import com.example.qrcode.model.QrCode;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends BaseActivity<ScanActivityBinding, ScanViewModel>
        implements ZXingScannerView.ResultHandler {

    ZXingScannerView zXingScannerView;

    public ScanActivity(){
        super(ScanViewModel.class, R.layout.scan_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        try{
            QrCode code = getViewModel().decodeString(result.getText());
            if(code.getAppId().equals(getResources().getString(R.string.app_id))){
                if(!loadUserData().getPhone().equals(code.getPhone())){
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", code.getPhone());
                    gotoIntent(TransferActivity.class, bundle, true);
                    return;
                }
                else{
                    showToast(getResources().getString(R.string.text_barcode_invalid));
                }
            }
        }
        catch (Exception e){
            showToast(getResources().getString(R.string.text_error));
            e.printStackTrace();
        }
        zXingScannerView.resumeCameraPreview(this::handleResult);
    }
}
