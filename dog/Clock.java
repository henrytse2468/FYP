package com.example.dog;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Clock {
    private DecimalFormat tflz, tf;
    private String time;

    public Clock() {
        tf = new DecimalFormat("#0");
        tflz = new DecimalFormat("00");
    }

    public String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        StringBuffer buf = new StringBuffer();
        buf.append(tf.format(calendar.get(Calendar.HOUR_OF_DAY)));
        buf.append(':');
        buf.append(tflz.format(calendar.get(Calendar.MINUTE)));
        buf.append(':');
        buf.append(tflz.format(calendar.get(Calendar.SECOND)));
        time =  buf.toString();

        return time;
    }

    public int getCurrentHour(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public int getCurrentMinute(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }
}
