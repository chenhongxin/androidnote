package com.haoxitech.canzhaopin.ui.activity;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;

/**
 * Created by wangtao on 16/2/3.
 */
public class InfoEditActivity extends BaseTitleActivity {

    private String title;
    private String type;
    private String ori;
    private String hint;
    private int keyboardType = 0;
    private int maxNum = 99;

    private EditText editText;

    @Override
    public int getContentViewID() {
        return R.layout.activity_info_edit;
    }

    @Override
    public void initView() {
        super.initView();

        title = getIntent().getStringExtra("title");

        if (title != null) {
            setTitle(title);
        } else {
            setTitle("编辑");
        }

        rightText.setText("完成");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(this);

        type = getIntent().getStringExtra("type");
        ori = getIntent().getStringExtra("ori");
        hint = getIntent().getStringExtra("hint");
        keyboardType = getIntent().getIntExtra("kb", 0);
        maxNum = getIntent().getIntExtra("max", 99);

        editText = (EditText) findViewById(R.id.info_edit_text);

        if (type != null && type.equals("multi")) {
            editText.setMinLines(3);
            editText.setSingleLine(false);
        } else {
            editText.setMaxLines(1);
            editText.setSingleLine(true);
        }

        if (keyboardType == 1) {
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }

        if (hint != null) {
            editText.setHint(hint);
        }

        if (ori != null) {
            editText.setText(ori);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()) {
            case R.id.activity_right_text: {
                if (editText.getText().length() == 0) {
                    showToast("您尚未填写");
                    return;
                }
                if (editText.getText().length() > maxNum) {
                    showToast("请不要超过" + maxNum + "个字");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("results", editText.getText().toString().trim());
                setResult(RESULT_OK, intent);
                finish();
            }
            break;
        }
    }
}
