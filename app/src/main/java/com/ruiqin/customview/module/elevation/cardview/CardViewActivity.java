package com.ruiqin.customview.module.elevation.cardview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;

public class CardViewActivity extends BaseActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), CardViewActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
    }

    @Override
    protected int getFragmentId() {
        return 0;
    }
}
