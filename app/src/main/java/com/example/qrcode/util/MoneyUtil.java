package com.example.qrcode.util;

public class MoneyUtil {
    public static String convertMoney(String money){
        int count = 0;
        String newMoney = "";
        for(int i=money.length()-1; i>=0; i--){
            if(count == 3){
                newMoney = '.' + newMoney;
                count = 0;
            }
            count++;
            newMoney = money.charAt(i) + newMoney;
        }
        return newMoney;
    }

    public static String removeDotMoney(String money){
        String newMoney = "";
        for(int i=0; i<money.length(); i++){
            if(money.charAt(i) == '.')
                continue;
            newMoney += money.charAt(i);
        }
        return newMoney;
    }
}
