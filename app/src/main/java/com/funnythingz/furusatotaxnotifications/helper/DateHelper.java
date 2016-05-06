package com.funnythingz.furusatotaxnotifications.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {

    public static String convertGeneralDateFormatString(String dateString) {
        String f = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat format = new SimpleDateFormat(f);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat convertFormat = new SimpleDateFormat(f);
        convertFormat.setTimeZone(TimeZone.getDefault());
        try {
            Date date = convertFormat.parse(convertFormat.format(format.parse(dateString)));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int yyyy = calendar.get(Calendar.YEAR);
            int MM = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            int hh = calendar.get(Calendar.HOUR_OF_DAY);
            int mm = calendar.get(Calendar.MINUTE);
            return yyyy + "/" + MM + "/" + dd + " " + hh + ":" + mm;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
