package com.haoxitech.canzhaopin.utils.mutiphotochooser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;


import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.adapter.PhotoDirectoryAdapter;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.model.ImageBucketItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangtao on 16/1/19.
 */
public class PhotoListActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener {

    private PhotoDirectoryAdapter adapter;
    private ListView listView;

    private ArrayList<ImageBucketItem> imageBucketItemArrayList = null;

    @Override
    public int getContentViewID() {
        return R.layout.activity_photo_list;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("相册列表");

        listView = (ListView) findViewById(R.id.listview);
        adapter = new PhotoDirectoryAdapter(this);
        listView.setAdapter(adapter);
        backBtn.setVisibility(View.GONE);

        imageBucketItemArrayList = (ArrayList<ImageBucketItem>) ImageMediaLoader.getInstance().getImagesBucketList(false);
        ImageBucketItem bucket = new ImageBucketItem();
        imageBucketItemArrayList.add(0, bucket);
        bucket.imageItemArrayList = ImageMediaLoader.getInstance().getImages();
        bucket.directoryName = "最近照片";
        adapter.setData(imageBucketItemArrayList);

        rightText.setText("取消");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        listView.setOnItemClickListener(this);
//        overridePendingTransition(R.anim.slide_in_from_left, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageBucketItemArrayList.clear();
//        ImageMediaLoader.getInstance().clearData();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ImageBucketItem item = (ImageBucketItem) adapterView.getAdapter().getItem(i);
        Intent intent = new Intent();

        intent.putExtra("choose", item.imageItemArrayList);
        intent.putExtra("index", i);
        setResult(RESULT_OK, intent);
        finish();
    }
}
