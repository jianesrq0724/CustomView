package com.ruiqin.customview.module.contacts;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.util.PhoneUtils;
import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;
import com.ruiqin.customview.util.ToastUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);
    }

    @Override
    protected int getFragmentId() {
        return 0;
    }


    @OnClick(R.id.button)
    public void onViewClicked() {
        getContacts();
    }


    private void getContacts() {
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            boolean askAgain = ActivityCompat.shouldShowRequestPermissionRationale(ContactsActivity.this, Manifest.permission.READ_CONTACTS);
            if (askAgain) {
                showAskUserDialog();
            } else {
                ActivityCompat.requestPermissions(ContactsActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            }
        } else {
            List<HashMap<String, String>> allContactInfo = PhoneUtils.getAllContactInfo();

            Intent intent = new Intent();
            intent.setAction("android.intent.action.PICK");
            intent.setType("vnd.android.cursor.dir/phone_v2");
            startActivityForResult(intent, 0);
        }
    }


    private void showAskUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("为了借款方便，需要获取通讯录权限");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(ContactsActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    boolean askAgain = ActivityCompat.shouldShowRequestPermissionRationale(ContactsActivity.this, permissions[0]);
                    if (!askAgain) {
                        ToastUtils.showShort("请手动打开权限");
                    }
                } else {
                    getContacts();
                }
                break;
            default:
                break;
        }
    }

    // 返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                String num = null;
                // 创建内容解析者
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(uri, null, null, null, null);
                while (cursor.moveToNext()) {
                    num = cursor.getString(cursor.getColumnIndex("data1"));
                }
                cursor.close();
                num = num.replaceAll("-", "").replaceAll("\\+86", "");
                ToastUtils.showShort(num);
            }

        }
    }
}
