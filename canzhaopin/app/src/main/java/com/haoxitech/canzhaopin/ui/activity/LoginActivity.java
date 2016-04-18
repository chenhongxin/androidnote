package com.haoxitech.canzhaopin.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.HaoUtility;
import com.haoxitech.HaoConnect.connects.UserConnect;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.common.RequestCommon;
import com.haoxitech.canzhaopin.utils.CheckUtils;
import com.haoxitech.canzhaopin.utils.SharedPreferencesTool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangtao on 16/3/15.
 */
public class LoginActivity extends BaseTitleActivity {

    private EditText telephoneText;
    private EditText passwordText;
    private Button loginBtn;
    private TextView registerBtn;
    private TextView forgetBtn;
    private Button backBtn;
    private String code;
    private boolean isPwd = false;

    @Override
    public int getContentViewID() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        telephoneText = (EditText) findViewById(R.id.telephone_text);
        passwordText = (EditText) findViewById(R.id.password_text);
        loginBtn = (Button) findViewById(R.id.login_btn);
        registerBtn = (TextView) findViewById(R.id.register_btn);
        forgetBtn = (TextView) findViewById(R.id.forget_btn);
        backBtn = (Button) findViewById(R.id.back_btn);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        forgetBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        telephoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInputView();
            }
        });

        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkInputView();
            }
        });
        findViewById(R.id.tv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code += "L";
                checkTheCode();
            }
        });
        findViewById(R.id.tv_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code += "R";
                checkTheCode();
            }
        });

        String pwd = getIntent().getStringExtra("isPwd");
        if(!TextUtils.isEmpty(pwd)){
            isPwd = Boolean.parseBoolean(pwd);
        }
    }

    private void checkInputView() {
        if ((telephoneText.getText().length() != 0) && passwordText.getText().length() != 0) {
            loginBtn.setTextColor(getResources().getColor(R.color.white));
            loginBtn.setEnabled(true);
        } else {
            loginBtn.setEnabled(false);
            loginBtn.setTextColor(getResources().getColor(R.color.lightGrayColor));
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.login_btn: {
                if (telephoneText.getText().length() == 0) {
                    showToast("请填写手机号");
                    return;
                }
                if (!CheckUtils.isMobileNO(telephoneText.getText().toString())) {
                    showToast("请填写正确的手机号");
                    return;
                }
                if (passwordText.getText().length() == 0) {
                    showToast("请填写密码");
                    return;
                }
                if (!CheckUtils.isPassWord(passwordText.getText().toString())) {
                    showToast("请填写正确的密码");
                    return;
                }

                Map<String, Object> params = new HashMap<>();
                params.put("account", telephoneText.getText().toString());
                params.put("password", HaoUtility.encodeMD5String(passwordText.getText().toString()));
                UserConnect.requestLogin(params, new HaoResultHttpResponseHandler() {
                    @Override
                    public void onSuccess(HaoResult result) {
                        if (result.isResultsOK()) {
                            if(isPwd){
                                setResult(RESULT_OK);
                            }
                            finish();
                        }
                        showToast("登录成功");
                    }

                    @Override
                    public void onFail(HaoResult result) {
                        super.onFail(result);
                        showToast(result.errorStr);
                    }
                }, this);
            }
                break;
            case R.id.register_btn: {
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, RequestCommon.REQUEST_REGISTER_ID);
            }
            break;
            case R.id.forget_btn: {
                Intent intent = new Intent(this, PasswordActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.back_btn: {
                finish();
            }
            break;
        }
    }

    private void checkTheCode() {
        if (code != null && code.length() >= 8 && code.toString().endsWith("LLRRLRRL")) {
//            startActivity(new Intent(this, HiddenSettingActivity.class));
            finish();
        }
    }
}
