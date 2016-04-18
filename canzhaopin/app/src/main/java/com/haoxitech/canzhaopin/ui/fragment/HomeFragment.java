package com.haoxitech.canzhaopin.ui.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.BannerConnect;
import com.haoxitech.HaoConnect.connects.JobConnect;
import com.haoxitech.HaoConnect.results.JobResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleListFragment;
import com.haoxitech.canzhaopin.common.JobType;
import com.haoxitech.canzhaopin.ui.activity.CompanyListActivity;
import com.haoxitech.canzhaopin.ui.activity.JobDetailActivity;
import com.haoxitech.canzhaopin.ui.activity.JobListActivity;
import com.haoxitech.canzhaopin.ui.activity.JobSearchActivity;
import com.haoxitech.canzhaopin.ui.adapter.ADVPagerAdapter;
import com.haoxitech.canzhaopin.ui.adapter.JobAdapter;
import com.haoxitech.canzhaopin.view.AdvViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangtao on 16/3/15.
 */
public class HomeFragment extends BaseTitleListFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private AdvViewPager bannerView;
    private LinearLayout bannerIndexView;
    private ArrayList listHeaderImageList;
    private List<ImageView> listPoint;

    private JobAdapter jobAdapter;

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

        JobConnect.requestList(params, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                listView.onRefreshComplete();
                Log.i("json", "result:" + result.results);
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
        }, getActivity());
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        setTitle("首页");
        backBtn.setVisibility(View.GONE);

        rightBtn.setBackgroundResource(R.drawable.search_btn);
        rightBtn.setOnClickListener(this);
        rightBtn.setVisibility(View.VISIBLE);

        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.home_top_view, null);
        listView.getRefreshableView().addHeaderView(headerView);

        headerView.findViewById(R.id.fulltime_layout).setOnClickListener(this);
        headerView.findViewById(R.id.parttime_layout).setOnClickListener(this);
        headerView.findViewById(R.id.internship_layout).setOnClickListener(this);
        headerView.findViewById(R.id.company_layout).setOnClickListener(this);

        bannerView = (AdvViewPager) headerView.findViewById(R.id.banner_view);
        bannerIndexView = (LinearLayout) headerView.findViewById(R.id.banner_index_view);

        jobAdapter = new JobAdapter(getActivity());
        listView.setAdapter(jobAdapter);
        listView.setOnItemClickListener(this);
        loadBanner();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_right_btn: {
                Intent intent = new Intent(getActivity(), JobSearchActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.fulltime_layout: {
                Intent intent = new Intent(getActivity(), JobListActivity.class);
                intent.putExtra("type", JobType.JOBTYPE_FULLTIME);
                intent.putExtra("title", "全职");
                startActivity(intent);
            }
            break;

            case R.id.parttime_layout: {
                Intent intent = new Intent(getActivity(), JobListActivity.class);
                intent.putExtra("type", JobType.JOBTYPE_PARTTIME);
                intent.putExtra("title", "兼职");
                startActivity(intent);
            }
            break;


            case R.id.internship_layout: {
                Intent intent = new Intent(getActivity(), JobListActivity.class);
                intent.putExtra("type", JobType.JOBTYPE_INTERNSHIP);
                intent.putExtra("title", "实习");
                startActivity(intent);
            }
            break;

            case R.id.company_layout: {
                Intent intent = new Intent(getActivity(), CompanyListActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

    private void loadBanner() {
        Map<String, Object> map = new HashMap<>();
        map.put("size", 99);
        map.put("page", 1);

        BannerConnect.requestList(map, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                listHeaderImageList = result.findAsList("results>");
                addPoint();
                bannerView.setAdapter(new ADVPagerAdapter(getActivity(), listHeaderImageList));
                if (bannerView.thread.isAlive()) {
                } else {
                    bannerView.thread.start();
                }
            }

            @Override
            public void onFail(HaoResult result) {
                super.onFail(result);
                showToast(result.errorStr);
            }
        }, getActivity());
    }

    private void addPoint() {
        bannerIndexView.removeAllViews();
        listPoint = new ArrayList<>();
        for (int i = 0; i < listHeaderImageList.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i < listHeaderImageList.size() - 1) {
                params.rightMargin = 10;
            } else {
                params.rightMargin = 0;
            }

            imageView.setLayoutParams(params);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.ic_point_checked);
            } else {
                imageView.setBackgroundResource(R.drawable.ic_point_normal);
            }
            listPoint.add(imageView);
            bannerIndexView.addView(imageView);
        }
        bannerView.listPoint = listPoint;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        JobResult jobResult = (JobResult) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent(getActivity(), JobDetailActivity.class);
        intent.putExtra("jobid", jobResult.findId().toString());
        startActivity(intent);
    }
}
