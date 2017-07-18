package com.lyz.rainbow.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ring on 17/7/17.
 */

public interface CalendarModel {

    void laodCalendar(Calendar calendar, LoadCalendarListener listener);

    interface LoadCalendarListener {
        void onComplete(ArrayList<Date> list);
    }
}
