package com.haoxitech.canzhaopin.ui.activity.user;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.HaoUtility;
import com.haoxitech.HaoConnect.connects.UserConnect;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.app.AppContext;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.base.BaseTitleAutoLayoutActivity;
import com.haoxitech.canzhaopin.ui.activity.LoginActivity;
import com.haoxitech.canzhaopin.utils.SharedPreferencesTool;

import java.util.HashMap;
import java.util.Map;

import butterknife.InjectView;

public class UserPwdUpdActivity extends BaseTitleAutoLayoutActivity {

    @InjectView(R.id.et_upd_pwd_old)
    EditText et_upd_pwd_old;
    @InjectView(R.id.et_upd_pwd_new)
    EditText et_upd_pwd_new;
    @InjectView(R.id.et_upd_pwd_again)
    EditText et_upd_pwd_again;
    @InjectView(R.id.b_upd_pwd_update)
    Button b_upd_pwd_update;

    @Override
    public int getContentViewID() {
        return R.layout.activity_user_pwd_upd;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("修改密码");
        b_upd_pwd_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i("json", "userId:" + SharedPreferencesTool.getParam(getApplicationContext(), "telephone", "") + "");
//        return;
        super.onClick(view);
        switch (view.getId()){
            case R.id.b_upd_pwd_update:
                params.clear();
                String old_pwd = et_upd_pwd_old.getText().toString();
                String new_pwd = et_upd_pwd_new.getText().toString();
                String again_pwd = et_upd_pwd_again.getText().toString();
                if(TextUtils.isEmpty(old_pwd)){
                    showToast("请输入旧密码");
                    return;
                }
                if(TextUtils.isEmpty(new_pwd)){
                    showToast("请输入新密码");
                    return;
                }
                if(TextUtils.isEmpty(again_pwd)){
                    showToast("请输入确认密码");
                    return;
                }
                if(new_pwd.length() < 6 || new_pwd.length() > 16){
                    showToast("新密码长度不少于6位且不大于16位");
                    return;
                }
                if(again_pwd.length() < 6 || again_pwd.length() > 16){
                    showToast("新密码长度不少于6位且不大于16位");
                    return;
                }
                if(!new_pwd.equals(again_pwd)){
                    showToast("两次密码不一致");
                    return;
                }
                params.put("oldpassword", HaoUtility.encodeMD5String(old_pwd));
                params.put("newpassword", HaoUtility.encodeMD5String(new_pwd));
                UserConnect.requestUpdateWithOldpassword(params, new HaoResultHttpResponseHandler() {
                    @Override
                    public void onSuccess(HaoResult result) {
                        if (result.isResultsOK()) {
                            sendBroadcast(new Intent("com.haoxitech.mainactivity"));
                            Map<Object, Object> params = new HashMap<Object, Object>();
                            params.put("isPwd", true);
                            intentCallBack.startActivityIntent(LoginActivity.class, params);
                            AppContext.getInstance().setUserId("");
                            HaoConnect.setCurrentUserInfo("", "", "");
                            setResult(0x111);
                            finish();
                        }
                    }

                    @Override
                    public void onFail(HaoResult result) {
                        super.onFail(result);
                        showToast(result.errorStr + "");
                    }
                }, UserPwdUpdActivity.this);
                break;
        }
    }
}