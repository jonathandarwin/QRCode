package com.example.qrcode.app.pin;

import android.arch.lifecycle.ViewModel;

public class PinViewModel extends ViewModel {

    public boolean checkPin(String pin, String savedPin){
        return pin.equals(savedPin);
    }

    public String setDisplayPin(String pin){
        String displayPin = "";
        for(int i=0; i<pin.length(); i++){
            displayPin += "*";
        }
        return displayPin;
    }
}
