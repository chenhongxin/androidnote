package com.haoxitech.canzhaopin.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.FavoriteConnect;
import com.haoxitech.HaoConnect.connects.JobConnect;
import com.haoxitech.HaoConnect.results.CompanyResult;
import com.haoxitech.HaoConnect.results.JobResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.app.AppContext;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.database.DBHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wangtao on 16/3/16.
 */
public class JobDetailActivity extends BaseTitleActivity implements CompoundButton.OnCheckedChangeListener {

    private ToggleButton collectionBtn;

    private String jobID;
    private TextView jobTitleText;
    private TextView salaryText;
    private TextView jobInfoText;
    private TextView descText;
    private ImageView companyImage;
    private TextView companyNameText;
    private TextView companyAddressText;
    private TextView companyDescText;
    private TextView jobDescText;
    private TextView jobNeedText;
    private ImageView addressImage;
    private TextView distanceText;
    private TextView addressDetailText;
    private Button submitBtn;

    private String[] educationArray;
    private String[] scaleArray;

    private String companyID;

    @Override
    public int getContentViewID() {
        return R.layout.activity_job_detail;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("职位详情");

        jobID = getIntent().getStringExtra("jobid");

        collectionBtn = (ToggleButton) findViewById(R.id.activity_btn_collection);
        collectionBtn.setVisibility(View.VISIBLE);

        collectionbtn.setOnCheckedChangeListener(null);

        jobTitleText = (TextView) findViewById(R.id.job_title_text);
        salaryText = (TextView) findViewById(R.id.salary_text);
        jobInfoText = (TextView) findViewById(R.id.job_info_text);
        descText = (TextView) findViewById(R.id.desc_text);
        companyImage = (ImageView) findViewById(R.id.company_image);
        companyNameText = (TextView) findViewById(R.id.company_name_text);
        companyAddressText = (TextView) findViewById(R.id.company_address_text);
        companyDescText = (TextView) findViewById(R.id.company_desc_text);
        jobDescText = (TextView) findViewById(R.id.job_desc_text);
        jobNeedText = (TextView) findViewById(R.id.job_need_text);
        addressImage = (ImageView) findViewById(R.id.address_image);
        distanceText = (TextView) findViewById(R.id.distance_text);
        addressDetailText = (TextView) findViewById(R.id.address_detail_text);
        submitBtn = (Button) findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(this);

        educationArray = getResources().getStringArray(R.array.education_type);

        scaleArray = getResources().getStringArray(R.array.company_scale);

        findViewById(R.id.company_layout).setOnClickListener(this);

        loadData();
    }

    private void loadData() {
        params.clear();
        params.put("id", jobID);
        JobConnect.requestDetail(params, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    JobResult jobResult = (JobResult) result;
                    jobTitleText.setText(jobResult.findTitle().toString());
                    if (jobResult.find("salaryType").equals("1")) {
                        salaryText.setText("【"+ jobResult.find("salaryLabel").toString() + "】");
                    } else {
                        salaryText.setText("【面议】");
                    }

                    StringBuffer str = new StringBuffer();
                    str.append(DBHelper.query(new String[]{"areaName"}, "zipArea", new String[]{"id=" + jobResult.find("companyLocal>zipAreaID")}).get(0).get("areaName"));
                    str.append(" " + educationArray[Integer.parseInt("" + jobResult.find("educational"))]);
                    switch (Integer.parseInt("" + jobResult.find("genre")) ) {
                        case 1: {
                            str.append(" 全职");
                        }
                        break;
                        case 2: {
                            str.append(" 兼职");
                        }
                        break;
                        case 3: {
                            str.append(" 实习");
                        }
                        break;
                    }
                    jobInfoText.setText(str);

                    descText.setText("福利：" + jobResult.findWelfaresLabel());

                    ImageLoader.getInstance().displayImage(jobResult.find("companyLocal>logo").toString(), companyImage);
                    companyNameText.setText(jobResult.find("companyLocal>title").toString());
                    companyAddressText.setText(jobResult.find("companyLocal>zipAreaLabel").toString());
                    companyDescText.setText(scaleArray[Integer.parseInt(jobResult.find("companyLocal>scale") + "")] + "/" + jobResult.find("companyLocal>industryLabel"));

                    jobDescText.setText(jobResult.findJobDesc().toString());
                    jobNeedText.setText(jobResult.findJobClaim().toString());

                    addressDetailText.setText(jobResult.find("companyLocal>address") + "");

                    companyID = jobResult.findCompanyID().toString();

                    if (Boolean.parseBoolean("" + jobResult.findFavorite())) {
                        collectionBtn.setChecked(true);
                    } else {
                        collectionBtn.setChecked(false);
                    }
                    isCollected = Boolean.parseBoolean("" + jobResult.findFavorite());
                    collectionBtn.setOnCheckedChangeListener(JobDetailActivity.this);
                }
            }

            @Override
            public void onFail(HaoResult result) {
                super.onFail(result);
                showToast(result.errorStr);
            }
        }, this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.company_layout) {
            Intent intent = new Intent(this, CompanyDetailActivity.class);
            intent.putExtra("companyid", companyID);
            startActivity(intent);
        }
    }

    private  boolean isCollected = false;
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (AppContext.getInstance().getUserId() == null || AppContext.getInstance().getUserId().equals("")) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            collectionBtn.setChecked(false);
            return;
        } else {
            params.put("job_id", jobID);
            FavoriteConnect.requestFavorite(params, new HaoResultHttpResponseHandler() {
                @Override
                public void onSuccess(HaoResult result) {
                    if (isCollected) {
                        showToast("取消收藏");
                    } else {
                        showToast("收藏成功");
                    }
                    isCollected = !isCollected;

                    collectionBtn.setChecked(isCollected);
                }
            }, this);
        }
    }
}
