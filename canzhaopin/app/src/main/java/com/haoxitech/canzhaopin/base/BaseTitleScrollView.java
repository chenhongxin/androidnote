package com.haoxitech.canzhaopin.base;

import android.view.View;
import android.widget.ScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.haoxitech.canzhaopin.R;

/**
 * Created by Guojie on 2016/2/16.
 */
public abstract class BaseTitleScrollView  extends BaseTitleFragment{
//    protected PullToRefreshScrollView mPullRefreshScrollView;
//    @Override
//    public void initView(View view) {
//        super.initView(view);
//        mPullRefreshScrollView= (PullToRefreshScrollView) view.findViewById(R.id.pl_mPullRefreshScrollView);
//        mPullRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                loadData();
//            }
//        });
//    }

    /**
     * 下拉刷新用于加载数据
     */
    public abstract void loadData();
}
