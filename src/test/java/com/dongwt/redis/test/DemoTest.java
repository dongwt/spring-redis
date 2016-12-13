package com.dongwt.redis.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoTest {

    public static void main(String[] args) throws ParseException {
           
        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.SECOND,0);
//        calendar.set(Calendar.MINUTE,0);
//        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(date) + " 00:00:00";
        date = format.parse(dateStr);
        
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        System.out.println(timeFormat.format(date));
        
        
        
        
    }
}
