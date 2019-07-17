package com.example.qrcode.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.qrcode.BR;

import java.util.List;

public class User extends BaseObservable {
    protected String phone;
    protected String balance;
    protected String pin;
    protected List<Transaction> listTransaction;

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

    @Bindable
    public String getPin() {
        return pin;
    }

    public User setPin(String pin) {
        this.pin = pin;
        notifyPropertyChanged(BR.pin);
        return this;
    }

    @Bindable
    public List<Transaction> getListTransaction() {
        return listTransaction;
    }

    public User setListTransaction(List<Transaction> listTransaction) {
        this.listTransaction = listTransaction;
        notifyPropertyChanged(BR.listTransaction);
        return this;
    }
}
