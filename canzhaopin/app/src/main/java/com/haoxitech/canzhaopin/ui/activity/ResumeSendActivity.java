package com.haoxitech.canzhaopin.ui.activity;

import android.view.View;
import android.widget.AdapterView;

import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.app.AppContext;
import com.haoxitech.canzhaopin.base.BaseTitleListActivity;
import com.haoxitech.canzhaopin.ui.adapter.JobAdapter;

/**
 * Created by wangtao on 16/3/17.
 */
public class ResumeSendActivity extends BaseTitleListActivity implements AdapterView.OnItemClickListener {

    private JobAdapter adapter;

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
        
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_resume_send;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("我发送的职位");

        adapter = new JobAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        loadData(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
