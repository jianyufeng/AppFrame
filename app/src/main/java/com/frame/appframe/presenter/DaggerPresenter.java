package com.frame.appframe.presenter;

import com.frame.appframe.bean.User;
import com.frame.appframe.fragment.MyFragment;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/10.
 */

public class DaggerPresenter {
    User user;
    MyFragment fragment;
    @Inject
    public DaggerPresenter(MyFragment fragment,User user){
        this.fragment = fragment;
        this.user  = user;
    }
    public void showUserName(){
        fragment.showUserName(user.name);
    }
}
