package com.ruiqin.customview.module.home;

import com.ruiqin.customview.base.BaseModel;
import com.ruiqin.customview.base.BasePresenter;
import com.ruiqin.customview.base.BaseView;
import com.ruiqin.customview.module.home.adapter.MainRecyclerAdapter;
import com.ruiqin.customview.module.home.bean.MainRecyclerData;

import java.util.List;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public interface MainContract {
    interface Model extends BaseModel {
        List<MainRecyclerData> initData();
    }

    interface View extends BaseView {
        void setRecyclerAdapterSuccess(MainRecyclerAdapter mainRecyclerAdapter);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        abstract void setAdapter();
    }
}
