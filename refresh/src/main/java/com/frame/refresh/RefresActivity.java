package com.frame.refresh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

/**
 * Created by Administrator on 2017/10/30.
 */

public class RefresActivity extends AppCompatActivity {
    private int mOffset;
    private int mScrollY;

    private RefreshLayout layout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View parallax = findViewById(R.id.parallax); // 图
        layout = (RefreshLayout) findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        layout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                //下来刷新时处理的状态
                mOffset = offset / 2;
                parallax.setTranslationY(mOffset - mScrollY); //背景图 也缓慢向下拉动

            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int footerHeight, int extendHeight) {
                //释放刷新时处理的状态
                mOffset = offset / 2;
                parallax.setTranslationY(mOffset - mScrollY);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));
        QuickAdapter adapter = new QuickAdapter();
        recyclerView.setAdapter(adapter);
        TextView textView = new TextView(getApplicationContext());
        textView.setText("孔");
        adapter.setEmptyView(textView);
        adapter.addData("fdfdsfdsfs");
        adapter.addData("fdfdsfdsfs");
        adapter.addData("fdfdsfdsfs");
        adapter.addData("fdfdsfdsfs");
        adapter.addData("fdfdsfdsfs");
        textView.setText("head");
        adapter.addHeaderView(textView);
        textView.setText("food");
        adapter.addFooterView(textView);
    }

    public class QuickAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public QuickAdapter() {
            super(R.layout.listitem_movie_item);
        }



        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv, item);

        }
    }
}
