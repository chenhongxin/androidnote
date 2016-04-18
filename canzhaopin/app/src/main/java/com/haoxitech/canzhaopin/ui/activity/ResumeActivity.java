package com.haoxitech.canzhaopin.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.ResumeConnect;
import com.haoxitech.HaoConnect.connects.UserConnect;
import com.haoxitech.HaoConnect.library.HaoQiNiu;
import com.haoxitech.HaoConnect.results.ResumeResult;
import com.haoxitech.HaoConnect.results.UserResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.app.AppContext;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.common.RequestCommon;
import com.haoxitech.canzhaopin.ui.adapter.ResumeAdapter;
import com.haoxitech.canzhaopin.ui.manager.InfoManager;
import com.haoxitech.canzhaopin.utils.ViewUtils;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.PhotoPreviewActivity;
import com.haoxitech.canzhaopin.view.CircleImageView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by wangtao on 16/3/17.
 */
public class ResumeActivity extends BaseTitleActivity {
    @InjectView(R.id.head_image)
    CircleImageView headImage;
    @InjectView(R.id.info_name_text)
    TextView infoNameText;
    @InjectView(R.id.info_sex_text)
    TextView infoSexText;
    @InjectView(R.id.info_birthday_text)
    TextView infoBirthdayText;
    @InjectView(R.id.info_education_text)
    TextView infoEducationText;
    @InjectView(R.id.info_wordtime_text)
    TextView infoWordtimeText;
    @InjectView(R.id.info_num_text)
    TextView infoNumText;
    @InjectView(R.id.info_contact_text)
    TextView infoContactText;
    @InjectView(R.id.info_weichat_text)
    TextView infoWeichatText;
    @InjectView(R.id.info_qq_text)
    TextView infoQqText;
    @InjectView(R.id.work_list_view)
    ListView workListView;
    @InjectView(R.id.education_list_view)
    ListView educationListView;

    private ResumeAdapter workAdapter;
    private ResumeAdapter educationAdapter;

    private String resumeID;

    @Override
    public int getContentViewID() {
        return R.layout.activity_resume;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("简历");

        rightText.setText("预览");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(this);

//        workAdapter = new ResumeAdapter(this);
//        workListView.setAdapter(workAdapter);

        headImage.setOnClickListener(this);
        headImage.setIsCircle(true);
        findViewById(R.id.info_layout).setOnClickListener(this);
        loadResumeData();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.activity_right_text: {

            }
            break;
            case R.id.head_image: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                        return;
                    }
                }

                Intent intent = new Intent(this, PhotoPreviewActivity.class);
                startActivityForResult(intent, RequestCommon.REQUEST_REGISTER_PHOTO_ID);
            }
            break;
            case R.id.info_layout: {
                Intent intent = new Intent(this, ResumeInfoActivity.class);
                intent.putExtra("resumeid", resumeID);
                startActivity(intent);
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCommon.REQUEST_REGISTER_PHOTO_ID: {
                    ImageLoader.getInstance().displayImage("file://" + data.getStringExtra("imagePath"), headImage);
                    HaoQiNiu.requestUploadToQiNiuWithFile(new File(data.getStringExtra("imagePath")), new JsonHttpResponseHandler() {
                        @Override
                        public void onStart() {
                            super.onStart();
                        }

                        @Override
                        public void onProgress(long bytesWritten, long totalSize) {
                            super.onProgress(bytesWritten, totalSize);
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                            try {
                                String photo = response.get("urlPreview").toString();
                                if (photo != null) {
                                    updateInfo("avatar", photo);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                    }, this);
                }
                break;

            }
        }
    }

    private void updateInfo(String key, String infoValue) {

        progressDialog.startProgressDialog();
        params.clear();
        params.put(key, infoValue);
        ResumeConnect.requestAdd(params, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    showToast("更新完成");
                }
                progressDialog.stopProgressDialog();
            }

            @Override
            public void onFail(HaoResult result) {
                super.onFail(result);
                showToast(result.errorStr);
                progressDialog.stopProgressDialog();
            }
        }, this);
    }

    public void addEducation() {
        Intent intent = new Intent(this, EducationAddActivity.class);
        intent.putExtra("resumeid", resumeID);
        startActivity(intent);
    }

    private void loadResumeData() {
        UserConnect.requestGetMyDetail(null, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    UserResult userResult = (UserResult) result;
                    if (!(userResult.find("resumeID").toString()).equals("0")) {
                        resumeID = userResult.find("resumeID").toString();
                        loadResumeDetail();
                    } else {
                        addResume();
                    }
                }
            }

            @Override
            public void onFail(HaoResult result) {
                super.onFail(result);
                showToast(result.errorStr);
            }
        }, this);
    }

    private void addResume() {
        ResumeConnect.requestAdd(null, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    resumeID = result.find("results>id").toString();
                }
            }
        }, this);
    }

    private void loadResumeDetail() {
        params.clear();
        params.put("id", resumeID);
        ResumeConnect.requestDetail(params, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    ResumeResult resumeResult = (ResumeResult) result;
                    ImageLoader.getInstance().displayImage(resumeResult.find("avatar").toString(), headImage);
                    infoNameText.setText(resumeResult.find("username").toString());

                    if (resumeResult.findAsInt("sex") == 1) {
                        infoSexText.setText("男");
                    } else if (resumeResult.findAsInt("sex") == 2) {
                        infoSexText.setText("女");
                    } else {
                        infoSexText.setText("未知");
                    }

                    infoBirthdayText.setText(resumeResult.find("birthday").toString());
                    infoEducationText.setText(InfoManager.getEducationByID(resumeResult.find("educational").toString()));
                    infoWordtimeText.setText(resumeResult.find("experience") + "年");
                    infoNumText.setText(resumeResult.find("cardNumber").toString());
                    infoContactText.setText(resumeResult.find("telephone").toString());
                    infoWeichatText.setText(resumeResult.find("wx").toString());
                    infoQqText.setText(resumeResult.find("qq").toString());

                    if (resumeResult.findAsList("resumeEducationExperience").size() == 0) {

                        educationAdapter = new ResumeAdapter( ResumeActivity.this, true, 0);
                    } else {
                        educationAdapter = new ResumeAdapter( ResumeActivity.this, false, 0);
                        educationAdapter.setData(resumeResult.findAsList("resumeEducationExperience"));
                    }
                    educationListView.setAdapter(educationAdapter);

                    ViewUtils.setListViewHeight(educationListView);

                    if (resumeResult.findAsList("resumeExperience").size() == 0) {

                        workAdapter = new ResumeAdapter( ResumeActivity.this, true, 1);
                    } else {
                        workAdapter = new ResumeAdapter( ResumeActivity.this, false, 1);
                        workAdapter.setData(resumeResult.findAsList("resumeExperience"));
                    }
                    workListView.setAdapter(workAdapter);

                    ViewUtils.setListViewHeight(workListView);
                }
            }
        }, this);
    }
}
