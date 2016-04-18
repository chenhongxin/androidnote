package com.haoxitech.canzhaopin.ui.activity;

import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;

/**
 * Created by wangtao on 16/3/20.
 */
public class MessageDetailActivity extends BaseTitleActivity {
    @Override
    public int getContentViewID() {
        return R.layout.activity_message_detail;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("消息详情");
    }
}
