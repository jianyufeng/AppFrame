package com.frame.appframe.fragment;

import dagger.Component;

/**
 * Created by Administrator on 2017/10/10.
 */
@Component(modules=FragmentModel.class)
public interface  FragmentComponent {
    void inject(MyFragment fragment);
}
