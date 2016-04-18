package com.haoxitech.canzhaopin.base;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoxitech.canzhaopin.R;

/**
 * Created by wangtao on 16/1/19.
 */
public abstract class BaseTitleFragment extends BaseFragment{

    private TextView titleText;
    protected TextView rightText;
    protected ImageButton rightBtn;

    protected LinearLayout backBtn;


    @Override
    public void initView(View view) {
        titleText = (TextView) view.findViewById(R.id.activity_title_text);
        rightText = (TextView) view.findViewById(R.id.activity_right_text);
        rightBtn = (ImageButton) view.findViewById(R.id.activity_right_btn);

        backBtn = (LinearLayout) view.findViewById(R.id.activity_back_btn);
    }

    public void setTitle(String title)
    {
        titleText.setText(title);
    }
}
