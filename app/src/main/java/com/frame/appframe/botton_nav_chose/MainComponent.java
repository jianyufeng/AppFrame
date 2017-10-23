package com.frame.appframe.botton_nav_chose;

import dagger.Component;

/**
 * Created by Administrator on 2017/10/23/023.
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
