package com.oldmen.attendancemanager;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    private final static String DATE_FORM_FACTOR = "EEEE, dd MMM yyyy ";


    public static String changeFormat(long milliSec){

        Date date = new Date(milliSec);
        SimpleDateFormat df2 = new SimpleDateFormat(DATE_FORM_FACTOR);
        return df2.format(date);
    }

}
