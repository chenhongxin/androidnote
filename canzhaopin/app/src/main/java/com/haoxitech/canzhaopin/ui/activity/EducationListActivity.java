package com.haoxitech.canzhaopin.ui.activity;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.ResumeEducationExperienceConnect;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleListActivity;
import com.haoxitech.canzhaopin.ui.adapter.ResumeAdapter;

/**
 * Created by wangtao on 16/3/21.
 */
public class EducationListActivity extends BaseTitleListActivity {

    private ResumeAdapter adapter;

    @Override
    public void loadData(boolean isLoadMore) {
        params.clear();
        ResumeEducationExperienceConnect.requestList(params, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    adapter.setData(result.findAsList("results>"));
                }
            }
        }, this);
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_education_list;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("教育经历");
        adapter = new ResumeAdapter(this);
        listView.setAdapter(adapter);
        listView.setMode(PullToRefreshBase.Mode.DISABLED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(false);
    }
}
