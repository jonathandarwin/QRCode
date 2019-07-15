package com.example.qrcode.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.qrcode.model.User;

public class Auth {

    SharedPreferences sharedPreferences;

    public Auth(Context context){
        sharedPreferences = context.getSharedPreferences("QRCode", Context.MODE_PRIVATE);
    }

    public void saveUserData(User user){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("phone", user.getPhone());
        editor.putString("balance", user.getBalance());
        editor.apply();
    }

    public User loadUserData(){
        return new User()
                .setPhone(sharedPreferences.getString("phone", ""))
                .setBalance(sharedPreferences.getString("balance", ""));
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getString("email", "").equals("") ? false : true;
    }
}
