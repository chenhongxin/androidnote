package com.haoxitech.canzhaopin.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.canzhaopin.app.AppManager;
import com.haoxitech.canzhaopin.view.CustomProgressDialog;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;


/**
 * Created by wangtao on 15/11/17.
 */
public abstract class BaseAutoLayoutActivity extends AutoLayoutActivity {

    protected CustomProgressDialog progressDialog;
    protected Map<String, Object> params = new HashMap<>();
    protected IntentCallBack intentCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().pushActivity(this);
        progressDialog = new CustomProgressDialog(this);

        setContentView(getContentViewID());

        ButterKnife.inject(this);
        initView();

        intentCallBack = new IntentCallBack() {
            @Override
            public void startActivityCommon(Class cls) {
                Intent intent = new Intent(getApplicationContext(), cls);
                startActivity(intent);
            }

            @Override
            public void startActivityForResultCommon(Class cls, int result) {
                Intent intent = new Intent(getApplicationContext(), cls);
                startActivityForResult(intent, result);
            }

            @Override
            public void startActivityIntent(Class cls, Map<Object, Object> params) {
                Intent intent = new Intent(getApplicationContext(), cls);
                for(Map.Entry<Object, Object> entry : params.entrySet()){
                    intent.putExtra(entry.getKey() + "", entry.getValue() + "");
                }
                startActivity(intent);
            }

        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HaoConnect.cancelRequest(this);
        AppManager.getInstance().popActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    public void onPause() {
        super.onPause();
    }

    public abstract void initView();

    public abstract int getContentViewID();

    public void showToast(String notice) {
        Toast.makeText(this, notice, Toast.LENGTH_LONG).show();
    }

    public interface IntentCallBack{
        void startActivityCommon(Class cls);
        void startActivityForResultCommon(Class cls, int result);
        void startActivityIntent(Class cls, Map<Object, Object> params);
    }

}

