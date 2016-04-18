package com.haoxitech.canzhaopin.utils.mutiphotochooser;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import android.widget.TextView;

import com.haoxitech.HaoConnect.HaoUtility;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseActivity;
import com.haoxitech.canzhaopin.utils.ReleaseImp;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.adapter.PhotoChooseGridAdapter;
import com.haoxitech.canzhaopin.utils.mutiphotochooser.model.ImageItem;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import android.support.v4.app.ActivityCompat;


/**
 * Created by wangtao on 16/1/19.
 */
public class PhotoPreviewActivity extends BaseActivity {

    private PhotoChooseGridAdapter adapter;

    private GridView photoGridView;

    private ArrayList<ImageItem> imageModelList = null;

    private String photoTempFile;

    private int type = 0; // 0 单选 1 多选

    private ReleaseImp releaseImp = new ReleaseImp();

    @Override
    public int getContentViewID() {
        return R.layout.activity_photo_preview;
    }

    @Override
    public void initView() {

        type = getIntent().getIntExtra("type", 0);

        photoGridView = (GridView) findViewById(R.id.photo_preview_gridview);
        adapter = new PhotoChooseGridAdapter(this, type, releaseImp);
        photoGridView.setAdapter(adapter);


        imageModelList = ImageMediaLoader.getInstance().getImages();

        adapter.setData(imageModelList);

        PauseOnScrollListener pauseOnScrollListener = new PauseOnScrollListener(ImageLoader.getInstance(), true, true);
        photoGridView.setOnScrollListener(pauseOnScrollListener);

        TextView titleText = (TextView) findViewById(R.id.activity_title_text);
        titleText.setText("相册");

        TextView backText = (TextView) findViewById(R.id.activity_back_btn);
        backText.setText("相册");
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhotoPreviewActivity.this, PhotoListActivity.class);
                startActivityForResult(intent, 1000);
            }
        });

        TextView rightText = (TextView) findViewById(R.id.activity_right_text);
        rightText.setText("取消");
        rightText.setVisibility(View.VISIBLE);
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageModelList.clear();
        releaseImp.cleanBitmapList();
        HaoUtility.print("ondestroy");
    }

    public void setPhotoTempFile(String path) {
        this.photoTempFile = path;
    }

    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            // @SuppressWarnings("deprecation")

            final File imageFile = new File(
                    photoTempFile);
            if (imageFile.exists()) {
                imageFile.delete();
            }
            try {
                imageFile.createNewFile();
            } catch (Exception e) {

            }
            FileOutputStream fOutputStream = null;
            try {
                fOutputStream = new FileOutputStream(imageFile);
                photo.compress(Bitmap.CompressFormat.JPEG, 100, fOutputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fOutputStream.flush();
                fOutputStream.close();
            } catch (Exception e) {

            }

            Intent intent = new Intent();
            intent.putExtra("imagePath", photoTempFile);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    File temp = new File(photoTempFile);
                    startPhotoZoom(Uri.fromFile(temp));
                }
                break;

                case 2: {
                    startPhotoZoom(data.getData());
                }
                break;

                case 3:
                    if (data != null) {
                        setPicToView(data);
                    }
                    break;
                case 1000: {
                    if (imageModelList != null && imageModelList.size() > 0) {
                        imageModelList.clear();
                    }
                    imageModelList = (ArrayList<ImageItem>) data.getSerializableExtra("choose");
                    int index = data.getIntExtra("index", 0);

                    adapter.setShowCamera(index == 0 ? true : false);
                    adapter.setData(imageModelList);
                }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED && requestCode == 1000) {
            finish();
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }
}
