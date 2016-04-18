package com.haoxitech.canzhaopin.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.JobConnect;
import com.haoxitech.HaoConnect.results.JobResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleListActivity;
import com.haoxitech.canzhaopin.common.JobType;
import com.haoxitech.canzhaopin.ui.adapter.JobAdapter;

/**
 * Created by wangtao on 16/3/16.
 */
public class JobListActivity extends BaseTitleListActivity implements AdapterView.OnItemClickListener {

    private JobAdapter jobAdapter;
    private String title;
    private int jobType;

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
        params.put("genre", jobType);

        JobConnect.requestList(params, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                listView.onRefreshComplete();
                if (page == 1) {
                    jobAdapter.setData(result.findAsList("results>"));
                    if (jobAdapter.getData().size() == 0) {
                        listviewStatusLayout.setVisibility(View.VISIBLE);
                    } else {
                        listviewStatusLayout.setVisibility(View.GONE);
                    }
                } else {
                    jobAdapter.addData(result.findAsList("results>"));
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
        return R.layout.activity_job_list;
    }

    @Override
    public void initView() {
        super.initView();
        title = getIntent().getStringExtra("title");
        setTitle(title);

        jobType = getIntent().getIntExtra("type", JobType.JOBTYPE_FULLTIME);
        jobAdapter = new JobAdapter(this);
        listView.setAdapter(jobAdapter);
        listView.setOnItemClickListener(this);

        loadData(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        JobResult jobResult = (JobResult) adapterView.getAdapter().getItem(i);

        Intent intent = new Intent(this, JobDetailActivity.class);
        intent.putExtra("jobid", jobResult.findId().toString());
        startActivity(intent);
    }
}
