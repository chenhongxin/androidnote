package com.haoxitech.canzhaopin.utils.mutiphotochooser;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;


import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.adapter.PhotoGridAdapter;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.model.ImageItem;

import java.util.List;

/**
 * Created by wangtao on 16/1/19.
 */
public class PhotoSelectedActivity extends BaseTitleActivity {

    private GridView gridView;

    private PhotoGridAdapter gridAdapter;

    private List<ImageItem> imageModelList = null;

    @Override
    public int getContentViewID() {
        return R.layout.activity_photo_selected;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("已选照片");
        gridView = (GridView) findViewById(R.id.photo_select_gridview);
        gridAdapter = new PhotoGridAdapter(this);
        gridView.setAdapter(gridAdapter);
        findViewById(R.id.photo_add_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(PhotoSelectedActivity.this, PhotoSelectedActivity.class), 1);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
