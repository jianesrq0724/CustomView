package com.ruiqin.customview.module.slideview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;
import com.ruiqin.customview.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ScrollActivity extends BaseActivity {

    /**
     * 启动自身的实列
     *
     * @param context
     */
    public static void newIntance(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), ScrollActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.button)
    ImageView button;
    float lastX = 0;
    float lastY = 0;
    @BindView(R.id.textView)
    TextView textView;

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

    @OnClick(R.id.textView)
    public void onViewClicked() {
        ToastUtils.showShort("哈哈");
    }
}
