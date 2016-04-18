package com.haoxitech.canzhaopin.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.MessageLogConnect;
import com.haoxitech.HaoConnect.results.MessageLogResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleListFragment;
import com.haoxitech.canzhaopin.ui.activity.MessageDetailActivity;
import com.haoxitech.canzhaopin.ui.adapter.NoticeAdapter;

/**
 * Created by wangtao on 16/3/20.
 */
public class MessageFragment extends BaseTitleListFragment implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {
    private NoticeAdapter adapter;
    private int type = 1;
    private int currentIndex = 1;
    private RadioGroup radioGroup;

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
        params.put("m_type", type);
        MessageLogConnect.requestList(params, new HaoResultHttpResponseHandler() {
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
        }, getActivity());
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        setTitle("消息");

        adapter = new NoticeAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

//        radioGroup = (RadioGroup) view.findViewById(R.id.top_group);
//        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(false);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MessageLogResult logResult = (MessageLogResult) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent(getActivity(), MessageDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.resume_btn: {
                type = 1;
            }
            break;
            case R.id.invite_btn: {

                type = 2;
            }
            break;
            case R.id.notification_btn: {
                type = 3;
            }
            break;
        }

        if (currentIndex != type) {
            currentIndex = type;
            loadData(false);
        }
    }
}
