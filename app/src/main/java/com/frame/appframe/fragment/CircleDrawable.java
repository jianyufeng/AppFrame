package com.frame.appframe.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/12/18/018.
 */

public class CircleDrawable extends Drawable{

    public CircleDrawable(int mColor, int radius) {
        this.mRadius = radius;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mColor);
        paint.setDither(true);
        //绘制阴影，param1：模糊半径；param2：x轴大小：param3：y轴大小；param4：阴影颜色
        paint.setShadowLayer(10F, 15F, 15F, Color.GRAY);
    }

    private int mRadius;
    private Paint paint;
    @Override

    public void draw(@NonNull Canvas canvas) {
        canvas.drawCircle(mRadius+5, mRadius+5, mRadius+5, paint);
        canvas.drawCircle(mRadius, mRadius, mRadius, paint);
    }
    @Override
    public int getIntrinsicWidth() {
        return mRadius * 2;
    }

    @Override
    public int getIntrinsicHeight() {
        return mRadius * 2;
    }
    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
