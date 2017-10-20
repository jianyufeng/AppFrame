package com.frame.appframe.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frame.appframe.R;
import com.frame.appframe.presenter.DaggerPresenter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/10/9.
 */

public class MyFragment extends Fragment {
    @Inject
    DaggerPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my,container,false);
        tv = view.findViewById(R.id.tv);
        DaggerFragmentComponent.builder().fragmentModel(new FragmentModel(this)).build().inject(this);
        return view;
    }



    private TextView tv;
    public void showUserName(String name) {
        tv.setText(name);
    }
}
