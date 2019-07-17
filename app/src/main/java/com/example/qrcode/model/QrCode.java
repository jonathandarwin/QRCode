package com.example.qrcode.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.qrcode.BR;

public class QrCode extends BaseObservable {
    protected String phone;
    protected String appId;

    @Bindable
    public String getPhone() {
        return phone;
    }

    public QrCode setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
        return this;
    }

    @Bindable
    public String getAppId() {
        return appId;
    }

    public QrCode setAppId(String appId) {
        this.appId = appId;
        notifyPropertyChanged(BR.appId);
        return this;
    }
}
