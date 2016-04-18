package com.haoxitech.canzhaopin.base;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.haoxitech.canzhaopin.R;

/**
 * Created by wangtao on 16/1/19.
 */
public abstract class BaseTitleListFragment extends BaseTitleFragment{

    protected PullToRefreshListView listView;
    protected LinearLayout listviewStatusLayout;

    protected int page = 1;
    protected int pageSize = 10;

    @Override
    public void initView(View view) {
        super.initView(view);
        listView = (PullToRefreshListView) view.findViewById(R.id.listview);
        listView.setMode(PullToRefreshBase.Mode.BOTH);

        listviewStatusLayout = (LinearLayout) view.findViewById(R.id.listview_status);

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (refreshView.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                    loadData(false);
                } else {
                    loadData(true);
                }
            }
        });
    }

    public abstract void loadData(boolean isLoadMore);
}