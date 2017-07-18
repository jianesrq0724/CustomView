package com.ruiqin.customview.module.viewflipper;

import android.os.Bundle;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;

public class ViewFlipperActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
    }


    @Override
    protected int getFragmentId() {
        return 0;
    }
}
