package com.ruiqin.customview.module.elevation;

import android.os.Bundle;
import android.view.View;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;
import com.ruiqin.customview.module.elevation.cardview.CardViewActivity;
import com.ruiqin.customview.module.elevation.framelayout.FrameLayoutActivity;

import butterknife.OnClick;

public class ElevationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elevation);

    }

    @Override
    protected int getFragmentId() {
        return 0;
    }

    @OnClick({R.id.framelayout, R.id.cardview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.framelayout:
                startActivity(FrameLayoutActivity.newIntent(mContext));
                break;
            case R.id.cardview:
                startActivity(CardViewActivity.newIntent(mContext));
                break;
        }
    }
}
