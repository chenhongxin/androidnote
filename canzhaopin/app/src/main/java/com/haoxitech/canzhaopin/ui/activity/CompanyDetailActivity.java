package com.haoxitech.canzhaopin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.HaoUtility;
import com.haoxitech.HaoConnect.connects.CompanyConnect;
import com.haoxitech.HaoConnect.connects.JobConnect;
import com.haoxitech.HaoConnect.results.CompanyResult;
import com.haoxitech.HaoConnect.results.JobResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.ui.adapter.CompanyJobAdapter;
import com.haoxitech.canzhaopin.utils.DisplayUtil;
import com.haoxitech.canzhaopin.utils.ViewUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangtao on 16/3/17.
 */
public class CompanyDetailActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener {
    private ImageView companyImage;
    private TextView companyNameText;
    private TextView addressText;
    private TextView descText;
    private TextView addressInfoText;
    private ImageView addressImage;
    private TextView addressDetailText;
    private TextView distanceText;
    private TextView companyInfoText;

    @InjectView(R.id.listview)
    PullToRefreshListView listview;

    private CompanyJobAdapter jobAdapter;
    private String companyID;
    private String[] scaleArray;

    @Override
    public int getContentViewID() {
        return R.layout.activity_company_detail;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("公司详情");

        View topView = LayoutInflater.from(this).inflate(R.layout.company_top_view, null);
        companyImage = (ImageView) topView.findViewById(R.id.company_image);
        companyNameText = (TextView) topView.findViewById(R.id.company_name_text);
        addressText = (TextView) topView.findViewById(R.id.address_text);
        descText = (TextView) topView.findViewById(R.id.desc_text);
        addressInfoText = (TextView) topView.findViewById(R.id.address_info_text);
        addressImage = (ImageView) topView.findViewById(R.id.address_image);
        addressDetailText = (TextView) topView.findViewById(R.id.address_detail_text);
        distanceText = (TextView) topView.findViewById(R.id.distance_text);
        companyInfoText = (TextView) topView.findViewById(R.id.company_info_text);

        companyID = getIntent().getStringExtra("companyid");
        scaleArray = getResources().getStringArray(R.array.company_scale);

        listview.getRefreshableView().addHeaderView(topView);

        jobAdapter = new CompanyJobAdapter(this);
        listview.setAdapter(jobAdapter);
        listview.setMode(PullToRefreshBase.Mode.DISABLED);
        listview.setOnItemClickListener(this);

        loadData();
        loadJobData();
    }

    private void loadData() {
        params.clear();
        params.put("id", companyID);
        CompanyConnect.requestDetail(params, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    CompanyResult companyResult = (CompanyResult) result;
                    ImageLoader.getInstance().displayImage(companyResult.findLogo().toString(), companyImage);
                    companyNameText.setText(companyResult.findTitle().toString());
                    addressText.setText(companyResult.find("zipAreaLabel").toString());
                    descText.setText(scaleArray[Integer.parseInt(companyResult.find("scale") + "")] + "/" + companyResult.find("industryLabel"));
                    addressDetailText.setText(companyResult.findAddress().toString());
                    companyInfoText.setText(companyResult.findCompanyDesc().toString());

                }
            }
        }, this);
    }

    private void loadJobData() {
        params.clear();
        params.put("page", 1);
        params.put("size", 999);
        params.put("company_id", companyID);

        JobConnect.requestList(params, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {

                jobAdapter.setData(result.findAsList("results>"));
            }

            @Override
            public void onFail(HaoResult result) {
                super.onFail(result);
            }
        }, this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (l >= 0) {
            JobResult jobResult = (JobResult) adapterView.getAdapter().getItem(i);

            Intent intent = new Intent(this, JobDetailActivity.class);
            intent.putExtra("jobid", jobResult.findId().toString());
            startActivity(intent);
        }
    }
}

