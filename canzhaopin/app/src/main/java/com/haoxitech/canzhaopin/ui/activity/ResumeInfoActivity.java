package com.haoxitech.canzhaopin.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.ResumeConnect;
import com.haoxitech.HaoConnect.results.ResumeResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.common.RequestCommon;
import com.haoxitech.canzhaopin.ui.manager.InfoManager;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.InjectView;

/**
 * Created by wangtao on 16/3/21.
 */
public class ResumeInfoActivity extends BaseTitleActivity {

    @InjectView(R.id.info_name_text)
    TextView infoNameText;
    @InjectView(R.id.info_sex_text)
    TextView infoSexText;
    @InjectView(R.id.info_sex_layout)
    LinearLayout infoSexLayout;
    @InjectView(R.id.info_birthday_text)
    TextView infoBirthdayText;
    @InjectView(R.id.info_birthday_layout)
    LinearLayout infoBirthdayLayout;
    @InjectView(R.id.info_education_text)
    TextView infoEducationText;
    @InjectView(R.id.info_education_layout)
    LinearLayout infoEducationLayout;
    @InjectView(R.id.info_wordtime_text)
    TextView infoWordtimeText;
    @InjectView(R.id.info_wordtime_layout)
    LinearLayout infoWordtimeLayout;
    @InjectView(R.id.info_num_text)
    TextView infoNumText;
    @InjectView(R.id.info_num_layout)
    LinearLayout infoNumLayout;
    @InjectView(R.id.info_contact_text)
    TextView infoContactText;
    @InjectView(R.id.info_contact_layout)
    LinearLayout infoContactLayout;
    @InjectView(R.id.info_weichat_text)
    TextView infoWeichatText;
    @InjectView(R.id.info_weichat_layout)
    LinearLayout infoWeichatLayout;
    @InjectView(R.id.info_qq_text)
    TextView infoQqText;
    @InjectView(R.id.info_qq_layout)
    LinearLayout infoQqLayout;
    @InjectView(R.id.info_name_layout)
    LinearLayout infoNameLayout;

    private PopupWindow popupWindow;
    private int mYear;
    private int mMonth;
    private int mDay;

    private int yearStart;
    private int monthStart;
    private int dayStart;
    private boolean isShow = false;

    private String resumeID;

    @Override
    public int getContentViewID() {
        return R.layout.activity_resume_info;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("基本信息");
        infoNameLayout.setOnClickListener(this);
        infoSexLayout.setOnClickListener(this);
        infoBirthdayLayout.setOnClickListener(this);
        infoEducationLayout.setOnClickListener(this);
        infoWordtimeLayout.setOnClickListener(this);
        infoNumLayout.setOnClickListener(this);
        infoContactLayout.setOnClickListener(this);
        infoWeichatLayout.setOnClickListener(this);
        infoQqLayout.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date mydate = new Date(); // 获取当前日期Date对象
        calendar.setTime(mydate);// //为Calendar对象设置时间为当前日期

        yearStart = calendar.get(Calendar.YEAR); // 获取Calendar对象中的年
        monthStart = calendar.get(Calendar.MONTH);// 获取Calendar对象中的月
        dayStart = calendar.get(Calendar.DAY_OF_MONTH);// 获取这个月的第几天

        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        resumeID = getIntent().getStringExtra("resumeid");
        loadResumeDetail();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.info_name_layout: {
                Intent intent = new Intent(this, InfoEditActivity.class);
                intent.putExtra("hint", "请填写您的姓名");
                intent.putExtra("title", "昵称");
                intent.putExtra("ori", infoNameText.getText());
                intent.putExtra("max", 10);
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_USERNAME_ID);
            }
            break;
            case R.id.info_sex_layout: {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }

                View popView = LayoutInflater.from(this).inflate(R.layout.pop_sex_layout, null);

                popView.findViewById(R.id.sex_man_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                            popupWindow = null;
                            updateInfo("sex", "1");
                            infoSexText.setText("男");
                        }
                    }
                });
                popView.findViewById(R.id.sex_woman_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                            popupWindow = null;
                            updateInfo("sex", "2");
                            infoSexText.setText("女");
                        }
                    }
                });

                popView.findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                            popupWindow = null;
                        }
                    }
                });
                popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, true);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());

                popupWindow.showAtLocation(findViewById(R.id.resume_info_layout), Gravity.BOTTOM, 0, 0);
            }
            break;
            case R.id.info_birthday_layout: {
                isShow = false;
                onCreateDialog().show();
            }
            break;
            case R.id.info_education_layout: {
                Intent intent = new Intent(this, InfoChooseActivity.class);
                intent.putExtra("title", "选择学历");
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_EDUCATION_ID);
            }
            break;
            case R.id.info_wordtime_layout: {
                Intent intent = new Intent(this, InfoEditActivity.class);
                intent.putExtra("hint", "请填写您的工作年限");
                intent.putExtra("title", "工作年限");
//                intent.putExtra("ori", infoWordtimeText.getText());
                intent.putExtra("max", 10);
                intent.putExtra("kb", 1);
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_WORKTIME_ID);
            }
            break;
            case R.id.info_num_layout: {
                Intent intent = new Intent(this, InfoEditActivity.class);
                intent.putExtra("hint", "请填写您的残疾证编号");
                intent.putExtra("title", "残疾证编号");
                intent.putExtra("kb", 1);
//                intent.putExtra("ori", infoWordtimeText.getText());
                intent.putExtra("max", 20);
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_NUM_ID);
            }
            break;
            case R.id.info_contact_layout: {
                Intent intent = new Intent(this, InfoEditActivity.class);
                intent.putExtra("hint", "请填写您的联系电话");
                intent.putExtra("title", "联系电话");
                intent.putExtra("kb", 1);
//                intent.putExtra("ori", infoWordtimeText.getText());
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_CONTACT_ID);
            }
            break;
            case R.id.info_weichat_layout: {
                Intent intent = new Intent(this, InfoEditActivity.class);
                intent.putExtra("hint", "请填写您的微信号");
                intent.putExtra("title", "微信号");
//                intent.putExtra("ori", infoWordtimeText.getText());
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_WECHAT_ID);
            }
            break;
            case R.id.info_qq_layout: {
                Intent intent = new Intent(this, InfoEditActivity.class);
                intent.putExtra("hint", "请填写您的QQ号");
                intent.putExtra("title", "QQ号");
                intent.putExtra("kb", 1);
//                intent.putExtra("ori", infoWordtimeText.getText());
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_QQ_ID);
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case RequestCommon.REQUEST_EDIT_USERNAME_ID: {
                infoNameText.setText(data.getStringExtra("results"));
                if (infoNameText.getText().length() > 0) {
                    updateInfo("username", infoNameText.getText().toString());
                }
            }
            break;

            case RequestCommon.REQUEST_EDIT_WORKTIME_ID: {
                infoWordtimeText.setText(data.getStringExtra("results") + "年");
                if (infoWordtimeText.getText().length() > 0) {

                    updateInfo("experience", infoNameText.getText().toString());
                }
            }
            break;
            case RequestCommon.REQUEST_EDIT_CONTACT_ID: {
                infoContactText.setText(data.getStringExtra("results") + "");
                if (infoContactText.getText().length() > 0) {
                    updateInfo("telephone", infoContactText.getText().toString());
                }
            }
            break;
            case RequestCommon.REQUEST_EDIT_NUM_ID: {
                infoNumText.setText(data.getStringExtra("results") + "");
                if (infoNumText.getText().length() > 0) {
                    updateInfo("card_number", infoNumText.getText().toString());
                }
            }
            break;
            case RequestCommon.REQUEST_EDIT_WECHAT_ID: {
                infoWeichatText.setText(data.getStringExtra("results") + "");
                if (infoWeichatText.getText().length() > 0) {
                    updateInfo("wx", infoWeichatText.getText().toString());
                }
            }
            break;
            case RequestCommon.REQUEST_EDIT_QQ_ID: {
                infoQqText.setText(data.getStringExtra("results") + "");
                if (infoQqText.getText().length() > 0) {
                    updateInfo("qq", infoQqText.getText().toString());
                }
            }
            break;
            case RequestCommon.REQUEST_EDIT_EDUCATION_ID: {
                infoEducationText.setText(InfoManager.getEducationByID(data.getStringExtra("results") + ""));
                if (infoEducationText.getText().length() > 0) {
                    updateInfo("educational", data.getStringExtra("results") + "");
                }
            }
            break;
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

    private Dialog onCreateDialog() {

        Dialog dialog = null;

        dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        if (!isShow) {
                            isShow = true;
                            if (year > yearStart) {
                                showToast("您的生日不能大于当前时间");
                                return;
                            } else if (year == yearStart
                                    && monthOfYear > monthStart) {
                                showToast("您的生日不能大于当前时间");
                                return;
                            } else if (year == yearStart
                                    && monthOfYear == monthStart
                                    && dayOfMonth > dayStart) {
                                showToast("您的生日不能大于当前时间");
                                return;
                            } else {
                                if (mYear != year || mMonth != monthOfYear || mDay != dayOfMonth) {
                                    mYear = year;
                                    mMonth = monthOfYear;
                                    mDay = dayOfMonth;
                                    // 更新日期
                                    updateDateDisplay();
                                }
                            }
                        }
                    }
                }, mYear, mMonth, mDay);
        dialog.setTitle("选择生日");

        return dialog;
    }

    /**
     * 更新日期显示
     */
    private void updateDateDisplay() {
        StringBuilder buffer = new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                .append("-").append((mDay < 10) ? "0" + mDay : mDay);
        infoBirthdayText.setText(buffer.toString());
        updateInfo("birthday", "" + buffer);
    }

    private void loadResumeDetail() {
        params.clear();
        params.put("id", resumeID);
        ResumeConnect.requestDetail(params, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    ResumeResult resumeResult = (ResumeResult) result;
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
                }
            }
        }, this);
    }
}
