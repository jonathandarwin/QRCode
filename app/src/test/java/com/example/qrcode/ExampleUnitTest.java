package com.example.qrcode;

import com.example.qrcode.app.home.HomeActivity;
import com.example.qrcode.util.CalendarUtil;
import com.example.qrcode.util.MoneyUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {



    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void convertMoney(){assertEquals(MoneyUtil.convertMoney("123000"), "123.000");}

    @Test
    public void removeDotMoney(){assertEquals(MoneyUtil.removeDotMoney("1"), "1");}

    @Test
    public void convertViewToDate(){assertEquals(CalendarUtil.convertDateToView("2019-07-17"), "17 July 2019");}

    @Test
    public void convertDateToView(){assertEquals(CalendarUtil.convertViewToDate("17 July 2019"), "2019-07-17");}
}