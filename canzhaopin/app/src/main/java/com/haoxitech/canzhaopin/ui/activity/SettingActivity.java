package com.haoxitech.canzhaopin.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.UserConnect;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.app.AppContext;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.ui.activity.user.UserPwdUpdActivity;
import com.haoxitech.canzhaopin.ui.manager.DataCleanManager;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangtao on 16/3/18.
 */
public class SettingActivity extends BaseTitleActivity {
    @InjectView(R.id.cache_text)
    TextView cacheText;
    @InjectView(R.id.version_text)
    TextView versionText;
    @InjectView(R.id.pwd_layout)
    LinearLayout pwd_layout;
    int SETTINGACTIVITY_RESULT = 0x111;
    int SETTINGACTIVITY_REQUEST = 0x111;

    @Override
    public int getContentViewID() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("设置");

        findViewById(R.id.advice_layout).setOnClickListener(this);
        findViewById(R.id.cache_layout).setOnClickListener(this);
        findViewById(R.id.about_layout).setOnClickListener(this);
        findViewById(R.id.logout_btn).setOnClickListener(this);
        pwd_layout.setOnClickListener(this);

        versionText.setText("版本号:" + AppContext.getInstance().getVersionName());

        try {
            cacheText.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.advice_layout: {
                Intent intent = new Intent(this, AdviceActivity.class);
                startActivity(intent);
            }
                break;
            case R.id.cache_layout: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("温馨提示").setMessage("您需要清除缓存吗？").setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        try {
                            DataCleanManager.deleteFolderFile(Environment
                                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/canzhaopin", true);
                            DataCleanManager.clearAllCache(SettingActivity.this);
                            cacheText.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).show();
            }
            break;
            case R.id.about_layout: {
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.logout_btn: {
                UserConnect.requestLogOut(params, new HaoResultHttpResponseHandler() {
                    @Override
                    public void onSuccess(HaoResult result) {
                        AppContext.getInstance().setUserId("");
                        HaoConnect.setCurrentUserInfo("", "", "");

                        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                        startActivity(intent);
//                        setResult(RESULT_OK);
//                        finish();
                    }

                    @Override
                    public void onFail(HaoResult result) {
                        super.onFail(result);

                        AppContext.getInstance().setUserId("");
                        HaoConnect.setCurrentUserInfo("", "", "");

                        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                        startActivity(intent);
//                        setResult(RESULT_OK);
//                        finish();
                    }
                }, this);
            }
            break;
            case R.id.pwd_layout:
                Intent intent = new Intent(SettingActivity.this, UserPwdUpdActivity.class);
                startActivityForResult(intent, SETTINGACTIVITY_REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SETTINGACTIVITY_REQUEST){
            setResult(RESULT_OK);
            finish();
        }
    }
}
