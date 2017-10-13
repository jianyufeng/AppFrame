package com.frame.appframe.fragment;

import com.frame.appframe.bean.User;
import com.frame.appframe.presenter.DaggerPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/10/10.
 */
@Module
public class FragmentModel {
    public FragmentModel(MyFragment fragment) {
        this.fragment = fragment;
    }

    private MyFragment fragment;

    @Provides
    public MyFragment provideFragment(){
        return fragment;
    }
    @Provides
    public User provideUser(){
        return new User("123456789");
    }
    @Provides
    public DaggerPresenter provideDaggerPresenter(MyFragment fragment,User u){
        return new DaggerPresenter(fragment,u);
    }
}