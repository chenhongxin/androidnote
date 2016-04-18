package com.haoxitech.canzhaopin.ui.activity;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
public class PasswordActivity extends BaseTitleActivity {
    private EditText telephoneText;
    private EditText codeText;
    private Button codeBtn;
    private EditText passwordText;
    private Button submitBtn;

    private final int CODE_TYPE_FIND_PASSWORD = 3;

    MyCount count = new MyCount(60000, 1000);

    @Override
    public int getContentViewID() {
        return R.layout.activity_password;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("忘记密码");

        telephoneText = (EditText) findViewById(R.id.telephone_text);
        codeText = (EditText) findViewById(R.id.code_text);
        codeBtn = (Button) findViewById(R.id.code_btn);
        passwordText = (EditText) findViewById(R.id.password_text);
        submitBtn = (Button) findViewById(R.id.submit_btn);

        codeBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

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
            submitBtn.setTextColor(getResources().getColor(R.color.white));
            submitBtn.setEnabled(true);
        } else {
            submitBtn.setEnabled(false);
            submitBtn.setTextColor(getResources().getColor(R.color.lightGrayColor));
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
        super.onClick(view);
        switch (view.getId()) {
            case R.id.code_btn: {
                Map<String, Object> params = new HashMap<>();
                params.put("telephone", telephoneText.getText().toString());
                params.put("usefor", CODE_TYPE_FIND_PASSWORD);

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

            case R.id.submit_btn: {
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
                params.put("newpassword", HaoUtility.encodeMD5String(passwordText.getText().toString()));
                UserConnect.requestUpdateWithVerifyCode(params, new HaoResultHttpResponseHandler() {
                    @Override
                    public void onSuccess(HaoResult result) {
                       if (result.isResultsOK()) {
                           showToast("找回成功");
                           finish();
                       }
                    }
                }, this);
            }
                break;
        }
    }
}
