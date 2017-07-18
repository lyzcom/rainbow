package com.lyz.rainbow.presenter;

import com.lyz.rainbow.model.CalendarModel;
import com.lyz.rainbow.model.impl.CalendarModelImpl;
import com.lyz.rainbow.view.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ring on 17/7/17.
 */

public class CalendarPresenter implements CalendarModel.LoadCalendarListener {

    private CalendarModel calendarModel;
    private CalendarView calendarView; //在这里调用 视图操作

    public CalendarPresenter(CalendarView weatherView) {
        this.calendarView = weatherView;
        calendarModel = new CalendarModelImpl();
    }

    /**
     * 加载日历
     */
    public void loadCalendar(Calendar calendar) {
        calendarModel.laodCalendar(calendar ,this);
    }

    @Override
    public void onComplete(ArrayList<Date> list) {
        calendarView.showCalendar(list);
    }
}
