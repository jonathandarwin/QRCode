package com.example.qrcode.app.home.showqr;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
        getBinding().qrcode.setImageBitmap(getViewModel().generateCode(phone));
    }
}
