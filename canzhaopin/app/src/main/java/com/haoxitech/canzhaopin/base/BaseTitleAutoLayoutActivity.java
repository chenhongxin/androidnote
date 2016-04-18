package com.haoxitech.canzhaopin.base;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.haoxitech.canzhaopin.R;

/**
 * Created by wangtao on 16/1/19.
 */
public abstract class BaseTitleAutoLayoutActivity extends BaseAutoLayoutActivity implements View.OnClickListener {

    protected TextView titleText;
    protected LinearLayout backBtn;
    protected TextView rightText;
    protected ImageButton rightBtn;
    protected ToggleButton collectionbtn;

    @Override
    public void initView() {
        findViewById(R.id.activity_back_btn).setOnClickListener(this);
        titleText = (TextView) findViewById(R.id.activity_title_text);
        backBtn = (LinearLayout) findViewById(R.id.activity_back_btn);
        collectionbtn = (ToggleButton) findViewById(R.id.activity_btn_collection);
        backBtn.setOnClickListener(this);

        rightText = (TextView) findViewById(R.id.activity_right_text);
        rightBtn = (ImageButton) findViewById(R.id.activity_right_btn);
    }

    public void setTitle(String title) {
        titleText.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_back_btn: {
                finish();
            }
            break;
            default:
                break;
        }
    }
}
