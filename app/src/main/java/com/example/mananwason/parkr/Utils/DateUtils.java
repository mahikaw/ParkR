package com.example.mananwason.parkr.Utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mananwason on 7/27/17.
 */

public class DateUtils {
    SimpleDateFormat uiTimeFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
    SimpleDateFormat isoFormat = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss", Locale.ENGLISH);
    SimpleDateFormat uiDateFormat = new SimpleDateFormat("EEE dd MMM, yyyy", Locale.ENGLISH);

    public Calendar UiToCalendar(String date, String time) {
        Calendar calendar = Calendar.getInstance();

        try {
            Date uitime = uiTimeFormat.parse(time);
            calendar.setTime(uiDateFormat.parse(date));

            calendar.set(Calendar.HOUR_OF_DAY, uitime.getHours());// for 6 hour
            calendar.set(Calendar.MINUTE, uitime.getMinutes());// for 0 min
            calendar.set(Calendar.SECOND, uitime.getSeconds());// for 0 sec

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;

    }

    public String CalendarToISO(Calendar calendar) {
        return isoFormat.format(calendar.getTime());

    }
    public String CalendarToUI(Calendar calendar) {
        return uiDateFormat.format(calendar.getTime());

    }
}
