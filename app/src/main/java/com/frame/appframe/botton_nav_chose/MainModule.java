package com.frame.appframe.botton_nav_chose;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/10/23/023.
 */
@Module
public class MainModule {
    private final MainContract.View mView;

    public MainModule(MainContract.View view) {
        mView = view;
    }

    @Provides
    MainContract.View provideMainView() {
        return mView;
    }
}
