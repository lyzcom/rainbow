package com.lyz.rainbow.model.impl;

import com.lyz.rainbow.model.CalendarModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ring on 17/7/17.
 */

public class CalendarModelImpl implements CalendarModel {

    @Override
    public void laodCalendar(Calendar calendar,LoadCalendarListener listener) {

        ArrayList<Date> arrayList = new ArrayList<>();

        //克隆一个curCalendar
        Calendar curCalendar = (Calendar) calendar.clone();
        curCalendar.set(Calendar.DAY_OF_MONTH, 1); //将日期设置为1号

        //获取1号是星期几 比如1号是星期5，week = 5 将curCalendar日期提前4天,这样界面上1号就对应在星期五下面
        int week = curCalendar.get(Calendar.DAY_OF_WEEK);
        curCalendar.add(Calendar.DAY_OF_MONTH, -(week - 1));

        //日历中最大能出现42个单元格
        int maxCellCount = 42;
        while (arrayList.size() < maxCellCount) {
            arrayList.add(curCalendar.getTime());
            //add 到 list后 当前日历day+1
            curCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        listener.onComplete(arrayList);
    }
}
