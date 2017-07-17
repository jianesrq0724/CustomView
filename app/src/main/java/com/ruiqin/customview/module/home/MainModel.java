package com.ruiqin.customview.module.home;

import com.ruiqin.customview.module.dialog.DialogActivity;
import com.ruiqin.customview.module.elevation.ElevationActivity;
import com.ruiqin.customview.module.home.bean.MainRecyclerData;
import com.ruiqin.customview.module.textview.TextViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class MainModel implements MainContract.Model {
    @Override
    public List<MainRecyclerData> initData() {
        List<MainRecyclerData> recyclerDataList = new ArrayList<>();
        recyclerDataList.add(new MainRecyclerData("AlertDialog", DialogActivity.class));
        recyclerDataList.add(new MainRecyclerData("FrameLayout", ElevationActivity.class));
        recyclerDataList.add(new MainRecyclerData("TextViewActivity", TextViewActivity.class));
        return recyclerDataList;
    }
}
