package com.frame.appframe.botton_nav_chose;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/23/023.
 */

public class MainPresenter {
    //MainContract是个接口，View是他的内部接口，这里看做View接口即可
    public MainContract.View mView;

    @Inject
    MainPresenter(MainContract.View view) {
        mView = view;
    }
    public void loadData() {
        //调用model层方法，加载数据

        //回调方法成功时
        mView.updateUI();
    }


}
