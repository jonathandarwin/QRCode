package com.example.qrcode;

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
}