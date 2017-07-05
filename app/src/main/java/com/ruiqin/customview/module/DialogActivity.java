package com.ruiqin.customview.module;

import android.os.Bundle;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;

public class DialogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    @Override
    protected int getFragmentId() {
        return 0;
    }

    @Override
    public boolean canBack() {
        mToolbarTitle.setText("dialog");
        return super.canBack();
    }
}
