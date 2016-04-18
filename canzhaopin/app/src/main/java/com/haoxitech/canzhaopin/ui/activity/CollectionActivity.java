package com.haoxitech.canzhaopin.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.DeliveryConnect;
import com.haoxitech.HaoConnect.connects.FavoriteConnect;
import com.haoxitech.HaoConnect.results.FavoriteResult;
import com.haoxitech.HaoConnect.results.JobResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.app.AppContext;
import com.haoxitech.canzhaopin.base.BaseTitleListActivity;
import com.haoxitech.canzhaopin.ui.adapter.MineJobAdapter;

/**
 * Created by wangtao on 16/3/17.
 */
public class CollectionActivity extends BaseTitleListActivity implements AdapterView.OnItemClickListener {

    private MineJobAdapter adapter;

    @Override
    public void loadData(boolean isLoadMore) {
        params.clear();
        if (isLoadMore) {
            page++;
        } else {
            page = 1;
        }
        params.put("page", page);
        params.put("size", pageSize);
        params.put("user_id", AppContext.getInstance().getUserId());
        FavoriteConnect.requestList(params, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                listView.onRefreshComplete();
                if (page == 1) {
                    adapter.setData(result.findAsList("results>"));
                    if (adapter.getData().size() == 0) {
                        listviewStatusLayout.setVisibility(View.VISIBLE);
                    } else {
                        listviewStatusLayout.setVisibility(View.GONE);
                    }
                } else {
                    adapter.addData(result.findAsList("results>"));
                }
            }

            @Override
            public void onFail(HaoResult result) {
                super.onFail(result);
                listView.onRefreshComplete();
            }
        }, this);
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_collection;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("收藏");

        adapter = new MineJobAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        loadData(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FavoriteResult favoriteResult = (FavoriteResult) adapterView.getAdapter().getItem(i);
        JobResult jobResult = (JobResult) favoriteResult.find("jobLocal");

        Intent intent = new Intent(this, JobDetailActivity.class);
        intent.putExtra("jobid", jobResult.findId().toString());
        startActivity(intent);
    }
}
