package com.frame.daggertest;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

/***
 *�this contain left menu
 *  这是android 系统自带的控价实现的底部导航  不过有太多的局限性
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {
    @Inject
    MainPresenter mainPresenter;

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        DaggerMainComponent.builder().mainModule(new MainModule(this)).build().inject(this);
        mainPresenter.loadData();
    }


    @Override
    public void updateUI() {
        tv.setText("123456");
    }


}
