package com.haoxitech.canzhaopin.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.CompanyConnect;
import com.haoxitech.HaoConnect.connects.JobConnect;
import com.haoxitech.HaoConnect.results.CompanyResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleListActivity;
import com.haoxitech.canzhaopin.ui.adapter.CompanyAdapter;
import com.haoxitech.canzhaopin.ui.adapter.CompanyJobAdapter;

/**
 * Created by wangtao on 16/3/16.
 */
public class CompanyListActivity extends BaseTitleListActivity implements AdapterView.OnItemClickListener {

    private CompanyAdapter adapter;

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

        CompanyConnect.requestList(params, new HaoResultHttpResponseHandler() {
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
        return R.layout.activity_company_list;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("企业");

        adapter = new CompanyAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        loadData(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CompanyResult companyResult = (CompanyResult) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent(this, CompanyDetailActivity.class);
        intent.putExtra("companyid", companyResult.findId().toString());
        startActivity(intent);
    }
}
