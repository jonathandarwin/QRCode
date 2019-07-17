package com.example.qrcode.app.home.showqr;

import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;

import com.example.qrcode.model.QrCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.glxn.qrgen.android.QRCode;
import net.glxn.qrgen.core.scheme.VCard;

import org.json.JSONObject;

public class ShowQrViewModel extends ViewModel {

    Gson gson;

    public ShowQrViewModel(){
        gson = (new GsonBuilder()).create();
    }

    public Bitmap generateCode(String phone, String appId){
        try{
            QrCode data = new QrCode()
                    .setPhone(phone)
                    .setAppId(appId);
            JSONObject jsonObject = new JSONObject(gson.toJson(data));
            Bitmap code = QRCode.from(jsonObject.toString()).withSize(512,512).bitmap();
            return code;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
