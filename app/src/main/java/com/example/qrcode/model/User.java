package com.example.qrcode.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.qrcode.BR;

public class User extends BaseObservable {
    protected String phone;
    protected String balance;

    @Bindable
    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
        return this;
    }

    @Bindable
    public String getBalance() {
        return balance;
    }

    public User setBalance(String balance) {
        this.balance = balance;
        notifyPropertyChanged(BR.balance);
        return this;
    }
}
