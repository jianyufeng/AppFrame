package com.frame.appframe.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.appframe.R;
import com.frame.appframe.fragment.view.CustomCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */

public class MyFragment extends Fragment {
    private TextView tv;
    private CustomCalendar cal;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_my,container,false);
        tv = view.findViewById(R.id.tv);
        final ImageView ig = view.findViewById(R.id.ig);
        ig.setImageDrawable(new CircleDrawable(Color.RED,50));

        ig.postDelayed(new Runnable() {
            @Override
            public void run() {
                ig.setImageDrawable(getGrad());
            }
        },3000);
        cal = (CustomCalendar)view.findViewById(R.id.cal);
        //TODO 模拟请求当月数据
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(5);

        cal.setMarkDay("2018年1月", list);
        cal.setOnClickListener(new CustomCalendar.onClickListener() {
            @Override
            public void onLeftRowClick() {
                Toast.makeText(getContext(), "点击减箭头", Toast.LENGTH_SHORT).show();
                cal.monthChange(-1);
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(1000);
                           getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cal.setMarkDay(list);
                                }
                            });
                        }catch (Exception e){
                        }
                    }
                }.start();
            }

            @Override
            public void onRightRowClick() {
                Toast.makeText(getContext(), "点击加箭头", Toast.LENGTH_SHORT).show();
                cal.monthChange(1);
                new Thread(){
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(1000);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cal.setMarkDay(list);
                                }
                            });
                        }catch (Exception e){
                        }
                    }
                }.start();
            }

            @Override
            public void onTitleClick(String monthStr, Date month) {
                Toast.makeText(getContext(), "点击了标题："+monthStr, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWeekClick(int weekIndex, String weekStr) {
                Toast.makeText(getContext(), "点击了星期："+weekStr, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDayClick(int day, String dayStr, Integer finish) {
                Toast.makeText(getContext(), "点击了日期："+dayStr, Toast.LENGTH_SHORT).show();
                Log.w("", "点击了日期:"+dayStr);
            }
        });


        return view;
    }


    private Drawable getGrad(){
        //分别为开始颜色，中间夜色，结束颜色
        int colors[] = { 0xFFff0000 , 0xff00ff00, 0xff0000ff};
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColors(colors);
        drawable.setCornerRadius(160);
        drawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        drawable.setSize(160,160);
        drawable.setOrientation(GradientDrawable.Orientation.TL_BR);
        return drawable;
    }

}
