package com.example.qrcode.app.home.showqr;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.qrcode.R;
import com.example.qrcode.base.BaseActivity;
import com.example.qrcode.databinding.ShowQrDialogBinding;

public class ShowQrDialog extends BaseActivity<ShowQrDialogBinding, ShowQrViewModel> {

    public ShowQrDialog(){
        super(ShowQrViewModel.class, R.layout.show_qr_dialog);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String phone = loadUserData().getPhone();
        getBinding().setPhone(phone);
        Bitmap qrcode = getViewModel().generateCode(phone, getResources().getString(R.string.app_id));
        if(qrcode != null){
            getBinding().qrcode.setImageBitmap(qrcode);
        }
        else{
            getBinding().error.setVisibility(View.VISIBLE);
        }
    }
}
