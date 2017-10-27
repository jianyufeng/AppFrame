package com.frame.daggertest;

import dagger.Component;

/**
 * Created by Administrator on 2017/10/23/023.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
