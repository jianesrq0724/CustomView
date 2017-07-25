package com.ruiqin.customview.module.slideview;

import android.os.Bundle;
import android.view.View;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;

import butterknife.OnClick;

public class SlideViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_view);
    }

    @Override
    protected int getFragmentId() {
        return 0;
    }

    @OnClick({R.id.layout, R.id.scrollBy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout:
                ChangeLayoutActivity.newIntance(mContext);
                break;
            case R.id.scrollBy:
                ScrollActivity.newIntance(mContext);
                break;
        }
    }
}
