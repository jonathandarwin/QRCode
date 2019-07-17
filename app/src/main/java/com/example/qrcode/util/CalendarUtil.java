package com.example.qrcode.util;

import android.content.Context;

import com.example.qrcode.R;

import java.util.Calendar;

public class CalendarUtil {
    public static String getTime(Context context){
        Calendar cal = Calendar.getInstance();
        return String.format(context.getResources().getString(R.string.time_format), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
    }

    public static String getDate(Context context){
        Calendar cal = Calendar.getInstance();
        return String.format(context.getResources().getString(R.string.date_format), cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
    }

    public static String getViewDate(Context context){
        Calendar cal = Calendar.getInstance();
        return String.format(context.getResources().getString(R.string.date_view_format), cal.get(Calendar.DAY_OF_MONTH), getMonth(cal.get(Calendar.MONTH)), cal.get(Calendar.YEAR));
    }

    private static String getMonth(int month){
        String name = "";
        switch(month){
            case 0:
                name = "January";
                break;
            case 1:
                name = "February";
                break;
            case 2:
                name = "March";
                break;
            case 3:
                name = "April";
                break;
            case 4:
                name = "May";
                break;
            case 5:
                name = "June";
                break;
            case 6:
                name = "July";
                break;
            case 7:
                name = "August";
                break;
            case 8:
                name = "Septemer";
                break;
            case 9:
                name = "October";
                break;
            case 10:
                name = "November";
                break;
            case 11:
                name = "December";
                break;
        }
        return name;
    }
}
