package com.lyz.rainbow.view;

import java.util.ArrayList;
import java.util.Date;

/**
 * 天气相关view操作接口
 * Created by ring on 17/7/17.
 */

public interface CalendarView {

    void showCalendar(ArrayList<Date> list);

    void nextMonth();

    void preMonth();
}
