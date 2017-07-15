package com.ruiqin.customview.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;

public class TestActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), TestActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    protected int getFragmentId() {
        return 0;
    }
}
