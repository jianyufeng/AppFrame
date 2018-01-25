package com.frame.util.view.edit_view;

import android.graphics.Canvas;
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

    public CircleDrawable(int mColor,int radius) {
        this.mRadius = radius;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mColor);
        paint.setDither(true);
    }

    private int mRadius;
    private Paint paint;
    @Override

    public void draw(@NonNull Canvas canvas) {
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
