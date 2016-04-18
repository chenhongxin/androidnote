package com.haoxitech.canzhaopin.utils.mutiphotochooser;

import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by wangtao on 16/2/1.
 */
public class PhotoBigActivity extends BaseActivity {

    private ArrayList<String> imageItemArrayList;

    @Override
    public void initView() {
        imageItemArrayList = getIntent().getStringArrayListExtra("list");
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_photo_big;
    }
}
