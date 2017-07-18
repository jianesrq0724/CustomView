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

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.ruiqin.customview.R;
import com.ruiqin.customview.base.BaseActivity;
import com.ruiqin.customview.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
            getAllContactInfo().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<HashMap<String, String>>>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull List<HashMap<String, String>> hashMaps) throws Exception {
                            ToastUtils.showShort("haha");
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                        }
                    });

            Intent intent = new Intent();
            intent.setAction("android.intent.action.PICK");
            intent.setType("vnd.android.cursor.dir/phone_v2");
            startActivityForResult(intent, 0);
        }
    }

    private Flowable<List<HashMap<String, String>>> getAllContactInfo() {
        return Flowable.fromCallable(new Callable<List<HashMap<String, String>>>() {
            @Override
            public List<HashMap<String, String>> call() throws Exception {

                ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
                // 1.获取内容解析者
                ContentResolver resolver = Utils.getContext().getContentResolver();
                // 2.获取内容提供者的地址:com.android.contacts
                // raw_contacts表的地址 :raw_contacts
                // view_data表的地址 : data
                // 3.生成查询地址
                Uri raw_uri = Uri.parse("content://com.android.contacts/raw_contacts");
                Uri date_uri = Uri.parse("content://com.android.contacts/data");
                // 4.查询操作,先查询raw_contacts,查询contact_id
                // projection : 查询的字段
                Cursor cursor = resolver.query(raw_uri, new String[]{"contact_id"}, null, null, null);
                try {
                    // 5.解析cursor
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            // 6.获取查询的数据
                            String contact_id = cursor.getString(0);
                            // cursor.getString(cursor.getColumnIndex("contact_id"));//getColumnIndex
                            // : 查询字段在cursor中索引值,一般都是用在查询字段比较多的时候
                            // 判断contact_id是否为空
                            if (!StringUtils.isEmpty(contact_id)) {//null   ""
                                // 7.根据contact_id查询view_data表中的数据
                                // selection : 查询条件
                                // selectionArgs :查询条件的参数
                                // sortOrder : 排序
                                // 空指针: 1.null.方法 2.参数为null
                                Cursor c = resolver.query(date_uri, new String[]{"data1",
                                                "mimetype"}, "raw_contact_id=?",
                                        new String[]{contact_id}, null);
                                HashMap<String, String> map = new HashMap<String, String>();
                                // 8.解析c
                                if (c != null) {
                                    while (c.moveToNext()) {
                                        // 9.获取数据
                                        String data1 = c.getString(0);
                                        String mimetype = c.getString(1);
                                        // 10.根据类型去判断获取的data1数据并保存
                                        if (mimetype.equals("vnd.android.cursor.item/phone_v2")) {
                                            // 电话
                                            map.put("phone", data1);
                                        } else if (mimetype.equals("vnd.android.cursor.item/name")) {
                                            // 姓名
                                            map.put("name", data1);
                                        }
                                    }
                                }
                                // 11.添加到集合中数据
                                list.add(map);
                                // 12.关闭cursor
                                if (c != null) {
                                    c.close();
                                }
                            }
                        }
                    }
                } finally {
                    // 12.关闭cursor
                    if (cursor != null) {
                        cursor.close();
                    }
                }
                return list;
            }
        });
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
