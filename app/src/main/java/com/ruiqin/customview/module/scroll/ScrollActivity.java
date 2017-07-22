package com.ruiqin.customview.module.scroll;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;

import butterknife.BindView;

public class ScrollActivity extends BaseActivity {

    @BindView(R.id.button)
    ImageView button;
    float lastX = 0;
    float lastY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);


        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) (lastX - event.getRawX());
                        int dy = (int) (lastY - event.getRawY());
                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        Log.e("tag", dx + "");
                        Log.e("tag", dy + "");
                        button.scrollBy(dx, dy);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected int getFragmentId() {
        return 0;
    }
}
