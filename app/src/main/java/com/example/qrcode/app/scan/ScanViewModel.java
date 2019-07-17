package com.example.qrcode.app.scan;

import android.arch.lifecycle.ViewModel;

import com.example.qrcode.model.QrCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ScanViewModel extends ViewModel {

    Gson gson;

    public ScanViewModel(){
        gson = (new GsonBuilder()).create();
    }

    public QrCode decodeString(String data){
        try{
            QrCode code = gson.fromJson(data, QrCode.class);
            return code;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
