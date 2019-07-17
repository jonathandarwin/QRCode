package com.example.qrcode.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.qrcode.BR;

public class HeaderHistory extends BaseObservable implements BaseHistory {
    protected String date;

    @Bindable
    public String getDate() {
        return date;
    }

    public HeaderHistory setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
        return this;
    }

    @Override
    public int getItemType() {
        return BaseHistory.HEADER;
    }
}
