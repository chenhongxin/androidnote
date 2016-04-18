package com.haoxitech.canzhaopin.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.FeedbackConnect;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangtao on 16/3/9.
 */
public class AdviceActivity extends BaseTitleActivity {
    EditText inputText;

    @Override
    public int getContentViewID() {
        return R.layout.activity_advice;
    }

    @Override
    public void initView() {
        super.initView();

        inputText = (EditText) findViewById(R.id.input_text);
        setTitle("意见反馈");

        rightText.setVisibility(View.VISIBLE);
        rightText.setText("提交");
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(AdviceActivity.INPUT_METHOD_SERVICE);
                boolean isOpen = imm.isActive();
                if (isOpen == true) {
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }

                if (inputText.getText().length() == 0) {
                    return;
                }
                if (inputText.getText().length() > 255) {
                    showToast("您输入的内容请不要超过255个字");
                }
                params.put("content", inputText.getText().toString());
                progressDialog.startProgressDialog();
                FeedbackConnect.requestAdd(params, new HaoResultHttpResponseHandler() {
                    @Override
                    public void onSuccess(HaoResult result) {
                        showToast("提交成功");
                        inputText.setText("");
                        finish();
                        progressDialog.stopProgressDialog();
                    }

                    @Override
                    public void onFail(HaoResult result) {
                        super.onFail(result);
                        progressDialog.stopProgressDialog();
                    }
                }, AdviceActivity.this);
            }
        });
    }

}
