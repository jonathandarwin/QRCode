package com.example.qrcode.app.home.showqr;

import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;
import net.glxn.qrgen.android.QRCode;

public class ShowQrViewModel extends ViewModel {

    public Bitmap generateCode(String phone){
        Bitmap code = QRCode.from(phone).withSize(512,512).bitmap();
        return code;
    }
}
