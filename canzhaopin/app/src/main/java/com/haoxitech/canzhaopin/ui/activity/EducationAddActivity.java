package com.haoxitech.canzhaopin.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.ResumeEducationExperienceConnect;
import com.haoxitech.HaoConnect.library.HaoQiNiu;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.common.RequestCommon;
import com.haoxitech.canzhaopin.ui.manager.InfoManager;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by wangtao on 16/3/21.
 */
public class EducationAddActivity extends BaseTitleActivity {

    @InjectView(R.id.info_name_text)
    TextView infoNameText;
    @InjectView(R.id.info_name_layout)
    LinearLayout infoNameLayout;
    @InjectView(R.id.info_major_text)
    TextView infoMajorText;
    @InjectView(R.id.info_major_layout)
    LinearLayout infoMajorLayout;
    @InjectView(R.id.info_entrance_text)
    TextView infoEntranceText;
    @InjectView(R.id.info_entrance_layout)
    LinearLayout infoEntranceLayout;
    @InjectView(R.id.info_leave_text)
    TextView infoLeaveText;
    @InjectView(R.id.info_leave_layout)
    LinearLayout infoLeaveLayout;
    @InjectView(R.id.info_education_text)
    TextView infoEducationText;
    @InjectView(R.id.info_education_layout)
    LinearLayout infoEducationLayout;
    @InjectView(R.id.delete_btn)
    Button deleteBtn;

    private String educationId = null;
    private String resumeID;

    private int mYear;
    private int mMonth;
    private int mDay;

    private int leaveYear;
    private int leaveMonth;
    private int leaveDay;

    private int yearStart;
    private int monthStart;
    private int dayStart;
    private boolean isShow = false;

    @Override
    public int getContentViewID() {
        return R.layout.activity_education_add;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("教育经历");

        rightText.setText("保存");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(this);

        educationId = getIntent().getStringExtra("id");
        resumeID = getIntent().getStringExtra("resumeid");
        if (educationId == null || educationId.equals("")) {
            deleteBtn.setVisibility(View.GONE);
        } else {
            loadData();
            deleteBtn.setOnClickListener(this);
        }

        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date mydate = new Date(); // 获取当前日期Date对象
        calendar.setTime(mydate);// //为Calendar对象设置时间为当前日期

        yearStart = calendar.get(Calendar.YEAR); // 获取Calendar对象中的年
        monthStart = calendar.get(Calendar.MONTH);// 获取Calendar对象中的月
        dayStart = calendar.get(Calendar.DAY_OF_MONTH);// 获取这个月的第几天

        leaveYear = mYear = calendar.get(Calendar.YEAR);
        leaveMonth = mMonth = calendar.get(Calendar.MONTH);
        leaveDay = mDay = calendar.get(Calendar.DAY_OF_MONTH);

        infoNameLayout.setOnClickListener(this);
        infoMajorLayout.setOnClickListener(this);
        infoEntranceLayout.setOnClickListener(this);
        infoLeaveLayout.setOnClickListener(this);
        infoEducationLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.activity_right_text: {
                if (!params.containsKey("shool")) {
                    showToast("请填写学校名称");
                    return;
                }
                params.put("resume_id", resumeID);
                ResumeEducationExperienceConnect.requestAdd(params, new HaoResultHttpResponseHandler() {
                    @Override
                    public void onSuccess(HaoResult result) {
                        showToast("添加成功");
                        finish();
                    }

                }, this);
            }
            break;
            case R.id.info_name_layout: {
                Intent intent = new Intent(this, InfoEditActivity.class);
                intent.putExtra("hint", "请填写学校名称");
                intent.putExtra("title", "学校名称");
                intent.putExtra("ori", infoNameText.getText());
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_SCHOOLNAME_ID);
            }
            break;

            case R.id.info_major_layout: {

                Intent intent = new Intent(this, InfoEditActivity.class);
                intent.putExtra("hint", "请填写所学专业");
                intent.putExtra("title", "专业名称");
                intent.putExtra("ori", infoMajorText.getText());
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_MAJORNAME_ID);
            }
            break;
            case R.id.info_entrance_layout: {
                isShow = false;
                onEntranceCreateDialog().show();
            }
            break;

            case R.id.info_leave_layout: {
                if (!params.containsKey("learn_min")) {
                    showToast("请先选择入学时间");
                    return;
                }
                isShow = false;
                onLeaveCreateDialog().show();
            }
            break;

            case R.id.info_education_layout: {
                Intent intent = new Intent(this, InfoChooseActivity.class);
                intent.putExtra("title", "选择学历");
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_EDUCATION_ID);
            }
            break;
            case R.id.delete_btn: {

            }
            break;
        }
    }

    private void loadData() {

    }

    private Dialog onEntranceCreateDialog() {

        Dialog dialog = null;

        dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (!isShow) {
                            isShow = true;
                            if (year > yearStart) {
                                showToast("您的入学时间不能大于当前时间");
                                return;
                            } else if (year == yearStart
                                    && monthOfYear > monthStart) {
                                showToast("您的入学时间不能大于当前时间");
                                return;
                            } else if (year == yearStart
                                    && monthOfYear == monthStart
                                    && dayOfMonth > dayStart) {
                                showToast("您的入学时间不能大于当前时间");
                                return;
                            } else {
                                if (mYear != year || mMonth != monthOfYear || mDay != dayOfMonth) {
                                    mYear = year;
                                    mMonth = monthOfYear;
                                    mDay = dayOfMonth;
                                    // 更新日期
                                    updateEntranceDateDisplay();
                                }
                            }
                        }
                    }
                }, mYear, mMonth, mDay);
        dialog.setTitle("选择入学时间");

        return dialog;
    }

    /**
     * 更新日期显示
     */
    private void updateEntranceDateDisplay() {
        StringBuilder buffer = new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                .append("-").append((mDay < 10) ? "0" + mDay : mDay);
        infoEntranceText.setText(buffer.toString());
        params.put("learn_min", buffer.toString());
    }

    /**
     * 更新日期显示
     */
    private void updateLeaveDateDisplay() {
        StringBuilder buffer = new StringBuilder().append(leaveYear).append("-")
                .append((leaveMonth + 1) < 10 ? "0" + (leaveMonth + 1) : (leaveMonth + 1))
                .append("-").append((leaveDay < 10) ? "0" + leaveDay : leaveDay);
        infoLeaveText.setText(buffer.toString());
        params.put("learn_max", buffer.toString());
    }

    private Dialog onLeaveCreateDialog() {

        Dialog dialog = null;

        dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (!isShow) {
                            isShow = true;
                            if (year < mYear) {
                                showToast("您的毕业时间不能小于入学时间");
                                return;
                            } else if (year == mYear
                                    && monthOfYear < mMonth) {
                                showToast("您的毕业时间不能小于入学时间");
                                return;
                            } else if (year == mYear
                                    && monthOfYear == mMonth
                                    && dayOfMonth < mDay) {
                                showToast("您的毕业时间不能小于入学时间");
                                return;
                            } else {
                                if (leaveYear != year || leaveMonth != monthOfYear || leaveDay != dayOfMonth) {
                                    leaveYear = year;
                                    leaveMonth = monthOfYear;
                                    leaveDay = dayOfMonth;
                                    // 更新日期
                                    updateLeaveDateDisplay();
                                }
                            }
                        }
                    }
                }, leaveYear, leaveMonth, leaveDay);
        dialog.setTitle("选择毕业时间");

        return dialog;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case RequestCommon.REQUEST_EDIT_SCHOOLNAME_ID: {
                    infoNameText.setText(data.getStringExtra("results"));
                    params.put("shool", data.getStringExtra("results"));
                }
                break;
                case RequestCommon.REQUEST_EDIT_MAJORNAME_ID: {
                    infoMajorText.setText(data.getStringExtra("results"));
                    params.put("profession", data.getStringExtra("results"));
                }
                break;
                case RequestCommon.REQUEST_EDIT_EDUCATION_ID: {
                    infoEducationText.setText(InfoManager.getEducationByID(data.getStringExtra("results") + ""));
                    params.put("education", data.getStringExtra("results"));
                }
                break;
            }
        }
    }
}
