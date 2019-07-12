package com.example.qrcode.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.qrcode.BR;

public class User extends BaseObservable {
    protected String email;
    protected String password;
    protected String name;
    protected String balance;

    @Bindable
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
        return this;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
        return this;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
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
