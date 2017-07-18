package com.ruiqin.customview.module.viewflipper.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ruiqin.customview.R;

import java.util.Random;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class CustomViewFlipper extends ViewFlipper {
    private Context mContext;

    public CustomViewFlipper(Context context) {
        super(context, null);
    }

    public CustomViewFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        initViewFlipper();
        initViewFlipperDate();
        startFlipping();
    }

    /**
     * 初始化动画
     */
    private void initViewFlipper() {
        setFlipInterval(2000);
        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        animIn.setDuration(500);
        setInAnimation(animIn);
        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        animOut.setDuration(500);
        setOutAnimation(animOut);
    }

    Random random = new Random();

    /**
     *
     */
    private void initViewFlipperDate() {
        for (int i = 0; i < 20; i++) {
            TextView textView = new TextView(mContext);
            textView.setTextColor(ContextCompat.getColor(mContext, R.color.text_black));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            int num = random.nextInt(8999) + 1000;
            int money = (random.nextInt(5) + 1) * 500;
            String content = "恭喜尾号" + num + "客户成功借款" + money + "元";
            textView.setText(content);
            addView(textView);
        }
    }

}
