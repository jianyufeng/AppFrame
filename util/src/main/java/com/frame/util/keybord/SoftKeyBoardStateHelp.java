package com.frame.util.keybord;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

import com.frame.util.scree.ScreenUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class SoftKeyBoardStateHelp implements ViewTreeObserver.OnGlobalLayoutListener {

    public interface SoftKeyBoardStateListener {
        void onSoftKeyBoardOpened(int keyBoardHeightInPx);

        void onSoftKeyBoardClosed();
    }

    public SoftKeyBoardStateHelp(View activityRootView) {
        this(activityRootView, false);
    }
    public SoftKeyBoardStateHelp(View activityRootView, boolean isSoftKeyboardOpened) {
        this.activityRootView     = activityRootView;
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    private List<SoftKeyBoardStateListener> listeners = new LinkedList<>();
    private View activityRootView;


    private boolean isSoftKeyboardOpened;
    private int lastSoftKeyboardHeightInPx;

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        activityRootView.getWindowVisibleDisplayFrame(rect);

        int heightDiff = activityRootView.getRootView().getHeight() - (rect.bottom - rect.top);
        //变化的实际高度   =  窗体高度  -  状态栏高度  -  （假如有虚拟键盘高度）
        int changeHeight = heightDiff - ScreenUtil.getStatusBarHeight(activityRootView.getContext())
                - ScreenUtil.getNavigationBarHeight(activityRootView.getContext());
        if (!isSoftKeyboardOpened && changeHeight > 100) {
            isSoftKeyboardOpened = true;
            notifyOnSoftKeyBoardOpened(changeHeight);
        } else if (isSoftKeyboardOpened && changeHeight < 100) {
            isSoftKeyboardOpened = false;
            notifyOnSoftKeyboardClosed();
        }

    }
    public void addSoftKeyboardStateListener(SoftKeyBoardStateListener listener) {
        listeners.add(listener);
    }

    public void removeSoftKeyboardStateListener(SoftKeyBoardStateListener listener) {
        listeners.remove(listener);
    }

    private void notifyOnSoftKeyBoardOpened(int keyboardHeightInPx) {
        this.lastSoftKeyboardHeightInPx = keyboardHeightInPx;

        for (SoftKeyBoardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyBoardOpened(keyboardHeightInPx);
            }
        }
    }
    private void notifyOnSoftKeyboardClosed() {
        for (SoftKeyBoardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyBoardClosed();
            }
        }
    }

    public void onDestroy(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activityRootView.getRootView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
        } else {
            activityRootView.getRootView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }

    }
}
