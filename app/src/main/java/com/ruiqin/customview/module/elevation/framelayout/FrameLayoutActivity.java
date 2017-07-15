package com.ruiqin.customview.module.elevation.framelayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;

public class FrameLayoutActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), FrameLayoutActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
    }

    @Override
    protected int getFragmentId() {
        return 0;
    }
}
