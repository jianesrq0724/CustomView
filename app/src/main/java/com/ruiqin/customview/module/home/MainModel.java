package com.ruiqin.customview.module.home;

import com.ruiqin.customview.module.DialogActivity;
import com.ruiqin.customview.module.home.bean.MainRecyclerData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class MainModel implements MainContract.Model {
    @Override
    public List<MainRecyclerData> initData() {
        List<MainRecyclerData> recyclerDataList = new ArrayList<>();
        MainRecyclerData mainRecyclerData = new MainRecyclerData("AlertDialog" + new Random().nextInt(), DialogActivity.class);
        recyclerDataList.add(mainRecyclerData);
        return recyclerDataList;
    }
}
