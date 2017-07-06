package com.ruiqin.customview.module.dialog.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.ruiqin.customview.R;
import com.ruiqin.customview.util.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class CustomDialog extends Dialog {
    private OnClickListener mCancelButtonListener;
    private OnClickListener mSmsButtonListener;

    public CustomDialog(@NonNull Context context) {
        this(context, R.style.CustomDialogTheme);
    }

    public CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);
        ButterKnife.bind(this);
        initWindow();
    }

    /**
     * 取消按钮接口回调
     *
     * @param cancelButtonListener
     */
    public void setCancelButtion(OnClickListener cancelButtonListener) {
        mCancelButtonListener = cancelButtonListener;
    }

    private void initWindow() {
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
    }

    @OnClick({R.id.share_qq, R.id.share_wx_chat, R.id.share_wx_circle, R.id.share_sms, R.id.share_cancel})
    public void onClick(View view) {
        cancel();
        switch (view.getId()) {
            case R.id.share_qq:
                ToastUtils.showShort("qq");
                break;
            case R.id.share_wx_chat:
                ToastUtils.showShort("wx_chat");
                break;
            case R.id.share_wx_circle:
                ToastUtils.showShort("wx_circle");
                break;
            case R.id.share_sms:
                if (mSmsButtonListener != null) {
                    mSmsButtonListener.onClick();
                }
                break;
            case R.id.share_cancel:
                if (mCancelButtonListener != null) {
                    mCancelButtonListener.onClick();
                }
                break;
        }
    }

    public interface OnClickListener {
        void onClick();
    }
}
