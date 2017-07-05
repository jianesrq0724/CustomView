package com.ruiqin.customview.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.ruiqin.customview.App;


/**
 * Created by ruiqin.shen
 * 类说明：
 */

public class ClipboardUtils {
    public static void copyText(CharSequence text) {
        ClipboardManager clipboardManager = (ClipboardManager) App.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("text", text));
    }

    /**
     * 获取剪贴板的文本
     *
     * @return 剪贴板的文本
     */
    public static CharSequence getText() {
        ClipboardManager clipboard = (ClipboardManager) Utils.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).coerceToText(Utils.getContext());
        }
        return null;
    }
}
