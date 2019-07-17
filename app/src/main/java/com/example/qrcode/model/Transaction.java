package com.example.qrcode.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.qrcode.BR;

public class Transaction extends BaseObservable implements BaseHistory{

    public static final int TRANSFER_IN = 1;
    public static final int TRANSFER_OUT = 2;
    public static final int TOP_UP = 3;

    protected String date;
    protected String time;
    protected String phone;
    protected String amount;
    protected int type;

    @Bindable
    public String getDate() {
        return date;
    }

    public Transaction setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
        return this;
    }

    @Bindable
    public String getTime() {
        return time;
    }

    public Transaction setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
        return this;
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public Transaction setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
        return this;
    }

    @Bindable
    public String getAmount() {
        return amount;
    }

    public Transaction setAmount(String amount) {
        this.amount = amount;
        notifyPropertyChanged(BR.amount);
        return this;
    }

    @Bindable
    public int getType() {
        return type;
    }

    public Transaction setType(int type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
        return this;
    }

    @Override
    public int getItemType() {
        return BaseHistory.ITEM;
    }
}
