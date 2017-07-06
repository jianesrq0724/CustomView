package com.ruiqin.customview.module.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;
import com.ruiqin.customview.module.dialog.view.CustomDialog;
import com.ruiqin.customview.util.ToastUtils;

import butterknife.OnClick;

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


    AlertDialog.Builder alertDialog;

    /**
     * AlertDialog
     */
    private void showAlertDialog() {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(mContext);
            alertDialog.setMessage("message");
            alertDialog.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
        alertDialog.show();
    }

    CustomDialog mCustomDialog;

    /**
     * CustomAlertDialog
     */
    private void showCustomAlertDialog() {
        if (mCustomDialog == null) {
            mCustomDialog = new CustomDialog(mContext);
            mCustomDialog.setCancelButtion(new CustomDialog.OnClickListener() {
                @Override
                public void onClick() {
                    ToastUtils.showShort("cancel");
                }
            });
        }
        mCustomDialog.show();
    }

    @OnClick({R.id.btn_alertDialog, R.id.btn_custom_alertDialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_alertDialog:
                showAlertDialog();
                break;
            case R.id.btn_custom_alertDialog:
                showCustomAlertDialog();
                break;
        }
    }
}
