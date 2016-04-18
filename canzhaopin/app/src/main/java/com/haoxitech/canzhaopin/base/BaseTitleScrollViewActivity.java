//package com.haoxitech.canzhaopin.base;
//
//import android.app.Activity;
//import android.view.View;
//import android.widget.ListView;
//import android.widget.ScrollView;
//
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
//import com.haoxitech.canzhaopin.R;
//
///**
// * Created by Guojie on 2016/2/19.
// */
//public abstract class BaseTitleScrollViewActivity extends BaseTitleActivity {
//    protected PullToRefreshScrollView scrollView;
//    protected Activity activity;
//
//    protected int page = 1;
//    protected int pageSize = 10;
//
//    @Override
//    public void initView() {
//        super.initView();
//        activity=this;
//        scrollView = (PullToRefreshScrollView) findViewById(R.id.sc_load_more_comment);
//        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
//
//        scrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
//                    loadData(false);
//                } else {
//                    loadData(true);
//                }
//            }
//        });
//    }
//
//    public abstract void loadData(boolean isLoadMore);
//}
