package com.lyz.rainbow.activity.custom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lyz.rainbow.R;
import com.lyz.rainbow.activity.EssayActivity;
import com.lyz.rainbow.presenter.CalendarPresenter;
import com.lyz.rainbow.view.CalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 自定义日历
 * Created by ring on 17/7/14.
 */

public class RainbowCalendar extends LinearLayout implements CalendarView {

    private TextView curDateTxt;

    private GridView gridView;

    private Calendar calendar = Calendar.getInstance();

    private Animation animation;

    private CalendarPresenter calendarPresenter;

    public RainbowCalendar(Context context) {
        super(context);
        initCalendarView(context);
    }

    public RainbowCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initCalendarView(context);
    }

    public RainbowCalendar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCalendarView(context);
    }

    /**
     * init view and event
     *
     * @param context
     */
    private void initCalendarView(final Context context) {

        LayoutInflater.from(context).inflate(R.layout.calendar_view, this);

        gridView = (GridView) findViewById(R.id.grid_calendar);
        curDateTxt = (TextView) findViewById(R.id.tv_cur_date);
        ImageView leftImg = (ImageView) findViewById(R.id.iv_left);
        ImageView rightImg = (ImageView) findViewById(R.id.iv_right);

        TextView essayTxt = (TextView) findViewById(R.id.tv_essay);
        essayTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EssayActivity.class));
            }
        });

        leftImg.setOnClickListener(leftListener);
        rightImg.setOnClickListener(rightListener);

        calendarPresenter = new CalendarPresenter(this);
        calendarPresenter.loadCalendar(calendar);

        initAnimation(context);
    }

    /**
     * init animation
     *
     * @param context
     */
    private void initAnimation(Context context) {
        animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
        animation.setInterpolator(new LinearInterpolator());//匀速旋转
    }

    private OnClickListener leftListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //上一个月
            preMonth();
        }
    };

    private OnClickListener rightListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //下一个月
            nextMonth();
        }
    };

    //日历相关回调
    @Override
    public void showCalendar(ArrayList<Date> list) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年 MMM");
        curDateTxt.setText(simpleDateFormat.format(calendar.getTime()));
        gridView.setAdapter(new CalendarAdapter(getContext(), list));
    }

    @Override
    public void nextMonth() {
        calendar.add(Calendar.MONTH, 1);
        calendarPresenter.loadCalendar(calendar);
    }

    @Override
    public void preMonth() {
        calendar.add(Calendar.MONTH, -1);
        calendarPresenter.loadCalendar(calendar);
    }

    private class CalendarAdapter extends ArrayAdapter<Date> {

        private Context mContext;
        private LayoutInflater inflater;

        public CalendarAdapter(@NonNull Context context, ArrayList<Date> arrayList) {
            super(context, R.layout.calendar_item, arrayList);
            mContext = context;
            inflater = LayoutInflater.from(mContext);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            Date date = getItem(position);
            int day = date.getDate();

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.calendar_item, parent, false);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.tv_calendar_day);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_circle);

            textView.setText(String.valueOf(day));

            if (calendar.getTime().getMonth() != date.getMonth()) {
                //不是当月的日期 隐藏
                textView.setVisibility(GONE);
            }

            Date curDate = new Date();

            if (curDate.getDate() == day && curDate.getMonth() == date.getMonth() && curDate.getYear() == date.getYear()) {
                //如果是当天 高亮显示
                textView.setTextColor(Color.parseColor("#cc0000"));
                imageView.setVisibility(VISIBLE);
                imageView.startAnimation(animation);
            }
            return convertView;
        }
    }
}
