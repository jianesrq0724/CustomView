package com.ruiqin.customview.module.textview;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;

import java.util.Random;

import butterknife.BindView;

public class TextViewActivity extends BaseActivity {

    @BindView(R.id.viewFlipper)
    ViewFlipper mViewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        Random random = new Random();

        for (int i = 0; i < 600; i++) {
            int num = random.nextInt(8999) + 1000;
            String strNum = String.valueOf(num);
            TextView textView = new TextView(mContext);
            textView.setText(strNum);
            mViewFlipper.addView(textView);
        }
        mViewFlipper.setFlipInterval(1000);
        mViewFlipper.startFlipping();
    }

    @Override
    protected int getFragmentId() {
        return 0;
    }
}
