package com.haoxitech.canzhaopin.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleListActivity;
import com.haoxitech.canzhaopin.ui.adapter.ChooseAdapter;

import java.util.ArrayList;

/**
 * Created by wangtao on 16/3/21.
 */
public class InfoChooseActivity extends BaseTitleListActivity implements AdapterView.OnItemClickListener {

    private ChooseAdapter adapter;
    private String title;

    @Override
    public void loadData(boolean isLoadMore) {

    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_info_choose;
    }

    @Override
    public void initView() {
        super.initView();
        title = getIntent().getStringExtra("title");
        setTitle(title);

        adapter = new ChooseAdapter(this);
        String[] educationArray = getResources().getStringArray(R.array.education_type);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < educationArray.length; i++) {
            list.add(educationArray[i]);
        }
        adapter.setData(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        intent.putExtra("results", l + "");
        setResult(RESULT_OK, intent);
        finish();
    }
}
