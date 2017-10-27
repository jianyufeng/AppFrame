package com.frame.util.view.edit_view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.frame.util.R;

/**
 * Created by Administrator on 2017/10/27.
 */

public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener, View.OnTouchListener {
    //EditText 右侧删除按钮图片
    private Drawable mClearDrawable;


    public ClearEditText(Context context) {
        super(context);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片,获取图片的顺序是左上右下（0,1,2,3,）
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = ContextCompat.getDrawable(getContext(), R.drawable.search_clear);
        }
        //设置要绘制图片的部位
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        doClearDrawable();//处理图片的显示

        //设置drawable 的点击事件
        setOnTouchListener(this);
        //文本变化时处理
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                doClearDrawable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //聚焦时处理
        setOnFocusChangeListener(this);


    }

    /**
     * 当ClearEditText焦点发生变化的时候，
     * 输入长度为零，隐藏删除图标，否则，显示删除图标
     */
    private void doClearDrawable() {
        if ("".equals(getText().toString()) || !isFocused()) {
            setClearDrawableNull();
            return;
        }
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], mClearDrawable, getCompoundDrawables()[3]);

    }

    /**
     * 隐藏删除图标
     */
    private void setClearDrawableNull() {
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], null, getCompoundDrawables()[3]);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        doClearDrawable();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if(getCompoundDrawables()[2] == null) {
            return false;
        }

        if((event.getAction() != MotionEvent.ACTION_UP)
                || event.getX() <= (getWidth() - getPaddingRight() - mClearDrawable.getIntrinsicWidth())) {
            return false;

        }
        getText().clear();

        return false;
    }
}
