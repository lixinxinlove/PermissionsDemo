package com.lee.slidedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    LinearLayout llSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llSlide = (LinearLayout) findViewById(R.id.ll_slide);
    }

    int y = 0;
    int yMove = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = (int) event.getY();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)
                        llSlide.getLayoutParams();
                layoutParams.setMargins(0, (yMove - y) / 2, 0, 0);
                llSlide.setLayoutParams(layoutParams);
                //y = yMove;
                break;
            case MotionEvent.ACTION_UP:

                if (yMove - y > 1500) {
                    startActivity(new Intent(this,TweActivity.class));
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)
                            llSlide.getLayoutParams();
                    params.setMargins(0, 0, 0, 0);
                    llSlide.setLayoutParams(params);
                } else {
                    FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)
                            llSlide.getLayoutParams();
                    params.setMargins(0, 0, 0, 0);
                    llSlide.setLayoutParams(params);
                }

                break;

        }


        return super.onTouchEvent(event);
    }
}
