package com.ruiqin.customview.module.slideview;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;
import com.ruiqin.customview.util.LogUtils;

import butterknife.BindView;

public class ChangeLayoutActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView mImageView;

    public static void newIntance(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), ChangeLayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_layout);
        getLayoutSize();
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        mImageView.layout(300, 300, 300 + mImageViewWidth, 300 + mImageViewHeight);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int l = (int) event.getRawX();
                        int t = (int) event.getRawY();
                        int r = l + mImageViewWidth;
                        int b = t + mImageViewHeight;
                        mImageView.layout(l, t, r, b);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
    }

    private int mImageViewWidth;
    private int mImageViewHeight;

    private void getLayoutSize() {
        ViewTreeObserver viewTreeObserver = mImageView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    mImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    mImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                mImageViewWidth = mImageView.getWidth();
                mImageViewHeight = mImageView.getHeight();
            }
        });
    }

    @Override
    protected int getFragmentId() {
        return 0;
    }
}
