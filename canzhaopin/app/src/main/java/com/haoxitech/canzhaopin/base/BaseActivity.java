package com.haoxitech.canzhaopin.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.canzhaopin.app.AppManager;
import com.haoxitech.canzhaopin.view.CustomProgressDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;


/**
 * Created by wangtao on 15/11/17.
 */
public abstract class BaseActivity extends Activity {

    protected CustomProgressDialog progressDialog;

    protected Map<String, Object> params = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().pushActivity(this);
        progressDialog = new CustomProgressDialog(this);

        setContentView(getContentViewID());

        ButterKnife.inject(this);
        initView();
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
}

