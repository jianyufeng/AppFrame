package com.frame.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/10/30.
 */

public class ToastUtil {
    private  static Context c;
    private void setContext(Context c) {
        this.c = c;
    }





    private static Toast toast = null;
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static Object synObj = new Object();
    private static void showMessage(final String msg) {
        showMessage(msg, Toast.LENGTH_SHORT);

    }

    public static void showMessage(final int msg) {
        showMessage(msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个文本并且设置时长
     *
     * @param msg
     * @param len
     */
    public static void showMessage(final CharSequence msg, final int len) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) {
                    if (toast != null) {
                        toast.setText(msg);
                        toast.setDuration(len);
                    } else {
                        toast = Toast.makeText(c, msg, len);
                    }
                    toast.show();
                }
            }
        });

    }

    /**
     * 资源文件方式显示文本
     *
     * @param msg
     * @param len
     */
    public static void showMessage(final int msg, final int len) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                synchronized (synObj) {
                    if (toast != null) {
                        toast.setText(msg);
                        toast.setDuration(len);
                    } else {
                        toast = Toast.makeText(c, msg, len);
                    }
                }
            }
        });
    }

    public static void cancle() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
