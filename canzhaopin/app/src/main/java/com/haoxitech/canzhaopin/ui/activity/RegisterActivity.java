package com.haoxitech.canzhaopin.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.HaoUtility;
import com.haoxitech.HaoConnect.connects.SmsVerifyConnect;
import com.haoxitech.HaoConnect.connects.UserConnect;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.utils.CheckUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangtao on 16/3/15.
 */
public class RegisterActivity extends BaseTitleActivity {
    private EditText telephoneText;
    private EditText passwordText;
    private EditText codeText;
    private Button codeBtn;
    private Button registerBtn;

    MyCount count = new MyCount(60000, 1000);

    private final int CODE_TYPE_REGISTER = 1;

    @Override
    public int getContentViewID() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        telephoneText = (EditText) findViewById(R.id.telephone_text);
        passwordText = (EditText) findViewById(R.id.password_text);
        codeText = (EditText) findViewById(R.id.code_text);
        codeBtn = (Button) findViewById(R.id.code_btn);
        registerBtn = (Button) findViewById(R.id.register_btn);

        codeBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        findViewById(R.id.btn_back).setOnClickListener(this);

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

        codeText.addTextChangedListener(new TextWatcher() {
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
    }

    private void checkInputView() {
        if ((telephoneText.getText().length() != 0) && passwordText.getText().length() != 0 && codeText.getText().length() != 0) {
            registerBtn.setTextColor(getResources().getColor(R.color.white));
            registerBtn.setEnabled(true);
        } else {
            registerBtn.setEnabled(false);
            registerBtn.setTextColor(getResources().getColor(R.color.lightGrayColor));
        }
    }

    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            codeBtn.setText((millisUntilFinished / 1000) + "秒");
        }

        @Override
        public void onFinish() {
            codeBtn.setText("获取验证码");
            codeBtn.getBackground().setAlpha(255);
            codeBtn.setClickable(true);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        count.onFinish();
    }

    @Override
    public void onClick(View view) {
//        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_back: {
                finish();
            }
                break;
            case R.id.code_btn: {
                if (telephoneText.getText().length() == 0) {
                    showToast("请填写手机号");
                    return;
                }
                if (!CheckUtils.isMobileNO(telephoneText.getText().toString())) {
                    showToast("请填写正确的手机号");
                    return;
                }

                Map<String, Object> params = new HashMap<>();
                params.put("telephone", telephoneText.getText().toString());
                params.put("usefor", CODE_TYPE_REGISTER);

                count.start();
                codeBtn.setClickable(false);
                codeBtn.getBackground().setAlpha(120);

                SmsVerifyConnect.requestSendVerifyCode(params, new HaoResultHttpResponseHandler() {
                    @Override
                    public void onSuccess(HaoResult result) {
                        if (result.isResultsOK()) {
                            showToast("验证码发送成功");
                        }
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
                if (codeText.getText().length() == 0) {
                    showToast("请填写验证码");
                    return;
                }
                if (codeText.getText().length() != 6) {
                    showToast("请填写正确的验证码");
                    return;
                }
                Map<String, Object> params = new HashMap<>();
                params.put("telephone", telephoneText.getText().toString());
                params.put("verify_code", codeText.getText().toString());
                params.put("password", HaoUtility.encodeMD5String(passwordText.getText().toString()));
                UserConnect.requestAdd(params, new HaoResultHttpResponseHandler() {
                    @Override
                    public void onSuccess(HaoResult result) {
                        if (result.isResultsOK()) {
                            showToast("注册成功");
                            setResult(RESULT_OK);
                            finish();
                        }
                    }

                    @Override
                    public void onFail(HaoResult result) {
                        super.onFail(result);
                        showToast(result.errorStr);
                    }
                }, this);
            }
            break;
        }
    }
}
