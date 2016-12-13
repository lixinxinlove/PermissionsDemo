package com.lee.calendardemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.lee.calendardemo.R.id.tv_day;

public class MainActivity extends AppCompatActivity {

    private LinearLayout rootView;

    LinearLayout weekView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = (LinearLayout) findViewById(R.id.root_view);
        weekView = (LinearLayout) View.inflate(this, R.layout.item_week_view, null);

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(new Date());

        init(mCalendar);
    }

    private void init(Calendar c) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(c.getTime());

        int dayTime = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(calendar.DATE, -dayTime + 1);

        int weekOfMonth = calendar.get(Calendar.DAY_OF_WEEK);

        int count = getMonthDays(calendar);

        int d = 1;
        int sum = 1;
        while (true) {
            weekView = (LinearLayout) View.inflate(this, R.layout.item_week_view, null);
            for (int j = 1; j <= 7; j++) {
                View day = View.inflate(this, R.layout.item_day, null);
                day.setLayoutParams(new LinearLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT, 1.0f));
                TextView tvDay = (TextView) day.findViewById(tv_day);

                if (j < weekOfMonth  && sum == 1) {

                } else {
                    tvDay.setText(d + "");
                    d++;
                    if (d > count) {
                        break;
                    }
                }
                weekView.addView(day);
            }

            int childCount = weekView.getChildCount();

            if (childCount < 7 && sum > 1) {
                for (int i = 1; i <= 7 - childCount; i++) {
                    View dayView = View.inflate(this, R.layout.item_day, null);
                    dayView.setLayoutParams(new LinearLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT,
                            RelativeLayout.LayoutParams.MATCH_PARENT, 1.0f));
                    weekView.addView(dayView);
                }
            }
            sum++;
            rootView.addView(weekView);

            if (d > count) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
                String time = format.format(calendar.getTime());
                TextView tv = new TextView(this);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setText(time);
                rootView.addView(tv, 0);
                break;
            }
        }
    }


    //获取 当前月的天数
    private int getMonthDays(Calendar calendar) {

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(calendar.getTime());

        mCalendar.set(Calendar.DATE, 1);
        mCalendar.roll(Calendar.DATE, -1);
        return mCalendar.get(Calendar.DATE) + 1;
    }
}
