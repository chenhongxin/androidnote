package com.haoxitech.canzhaopin.ui.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.UserConnect;
import com.haoxitech.HaoConnect.library.HaoQiNiu;
import com.haoxitech.HaoConnect.results.UserResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.app.AppContext;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.common.RequestCommon;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.PhotoPreviewActivity;
import com.haoxitech.canzhaopin.view.CircleImageView;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cz.msebera.android.httpclient.Header;

/**
 * Created by wangtao on 16/3/18.
 */
public class UserInfoActivity extends BaseTitleActivity {


    @InjectView(R.id.image_head)
    CircleImageView imageHead;
    @InjectView(R.id.arrow_right)
    ImageView arrowRight;
    @InjectView(R.id.text_name)
    TextView textName;
    @InjectView(R.id.text_birthday)
    TextView textBirthday;
    @InjectView(R.id.text_telephone)
    TextView textTelephone;
    @InjectView(R.id.text_num)
    TextView textNum;
    @InjectView(R.id.text_expect_job)
    TextView textExpectJob;
    @InjectView(R.id.text_expect_salary)
    TextView textExpectSalary;
    @InjectView(R.id.text_sex)
    TextView textSex;

    private int mYear;
    private int mMonth;
    private int mDay;

    private int yearStart;
    private int monthStart;
    private int dayStart;
    private boolean isShow = false;

    private PopupWindow popupWindow;
    @Override
    public int getContentViewID() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("个人信息");

        imageHead.setIsCircle(true);

        findViewById(R.id.layout_user_head).setOnClickListener(this);
        findViewById(R.id.layout_user_name).setOnClickListener(this);
        findViewById(R.id.layout_birthday).setOnClickListener(this);
        findViewById(R.id.layout_telephone).setOnClickListener(this);
        findViewById(R.id.layout_num).setOnClickListener(this);
        findViewById(R.id.layout_positin).setOnClickListener(this);
        findViewById(R.id.layout_salary).setOnClickListener(this);
        findViewById(R.id.layout_sex).setOnClickListener(this);

        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date mydate = new Date(); // 获取当前日期Date对象
        calendar.setTime(mydate);// //为Calendar对象设置时间为当前日期

        yearStart = calendar.get(Calendar.YEAR); // 获取Calendar对象中的年
        monthStart = calendar.get(Calendar.MONTH);// 获取Calendar对象中的月
        dayStart = calendar.get(Calendar.DAY_OF_MONTH);// 获取这个月的第几天

        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        loadData();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.layout_user_head: {
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
            case R.id.layout_user_name: {
                Intent intent = new Intent(this, InfoEditActivity.class);
                intent.putExtra("hint", "请填写您的昵称(10字以内)");
                intent.putExtra("title", "昵称");
                intent.putExtra("ori", textName.getText());
                intent.putExtra("max", 10);
                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_NICKNAME_ID);
            }
            break;
            case R.id.layout_sex: {
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
                            textSex.setText("男");
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
                            textSex.setText("女");
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

                popupWindow.showAtLocation(findViewById(R.id.user_info_layout), Gravity.BOTTOM, 0, 0);
            }
                break;
            case R.id.layout_birthday: {
                isShow = false;
                onCreateDialog().show();
            }
            break;
            case R.id.layout_telephone: {
//                Intent intent = new Intent(this, InfoEditActivity.class);
//                intent.putExtra("hint", "请填写您的联系方式");
//                intent.putExtra("title", "联系方式");
//                intent.putExtra("ori", textTelephone.getText());
//                intent.putExtra("max", 10);
//                startActivityForResult(intent, RequestCommon.REQUEST_EDIT_NICKNAME_ID);
            }
            break;
            case R.id.layout_num: {

            }
            break;
            case R.id.layout_positin: {

            }
            break;
            case R.id.layout_salary: {

            }
            break;


        }
    }

    private void loadData() {
        UserConnect.requestGetMyDetail(null, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    UserResult userResult = (UserResult) result;
                    ImageLoader.getInstance().displayImage(userResult.findAvatar().toString(), imageHead);
                    textName.setText(userResult.findNickname().toString());
                    textTelephone.setText(userResult.findTelephone().toString());
                    textBirthday.setText(userResult.findBirthday().toString());

                    if (userResult.findAsInt("sex") == 1) {
                        textSex.setText("男");
                    } else if (userResult.findAsInt("sex") == 2) {
                        textSex.setText("女");
                    } else {
                        textSex.setText("未知");
                    }
                    textBirthday.setText(userResult.findAsString("birthday"));
                    String birthday = userResult.findAsString("birthday");
                    if (birthday.length() == 10) {
                        mYear = Integer.parseInt(birthday.substring(0, 4));
                        mMonth = Integer.parseInt(birthday.substring(5, 7)) - 1;
                        mDay = Integer.parseInt(birthday.substring(8, 10));
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

    private void updateInfo(String key, String infoValue) {

        progressDialog.startProgressDialog();
        params.clear();
        params.put(key, infoValue);
        params.put("id", AppContext.getInstance().getUserId());
        UserConnect.requestUpdate(params, new HaoResultHttpResponseHandler() {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCommon.REQUEST_REGISTER_PHOTO_ID: {
                    ImageLoader.getInstance().displayImage("file://" + data.getStringExtra("imagePath"), imageHead);
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
                case RequestCommon.REQUEST_EDIT_NICKNAME_ID: {
                    textName.setText(data.getStringExtra("results"));
                    if (textName.length() > 0) {
                        updateInfo("nickname", textName.getText().toString());
                    }
                }
                break;

            }
        }
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
     *
     * 更新日期显示
     */
    private void updateDateDisplay() {
        StringBuilder buffer = new StringBuilder().append(mYear).append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                .append("-").append((mDay < 10) ? "0" + mDay : mDay);
        textBirthday.setText(buffer.toString());
        updateInfo("birthday", "" + buffer);
    }
}
