package com.frame.util.scree;

/**
 * Created by Administrator on 2017/10/27.
 */

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * android 常用的尺寸转换工具类
 */
public class DensityUtil {
    /**
     * 获取屏幕dpi
     */

    public static float getSceenDpi(Context context) {
        WindowManager wn = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wn.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.densityDpi;
    }

    /**
     * dp转px
     * 以dp为基准进行转化为px,按照不同的dpi,用来保证在不同分辨率的屏幕上显示的高度或宽度的一致
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics());
    }
    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal,
                context.getResources().getDisplayMetrics());
    }
    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static int px2dp(Context context, float pxVal) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxVal/scale +0.5);
    }
    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static int  px2sp(Context context, float pxVal) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxVal / scale + 0.5);
    }



}
