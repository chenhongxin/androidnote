package com.haoxitech.canzhaopin.utils;

import android.graphics.Bitmap;
import android.view.View;

import com.haoxitech.HaoConnect.HaoUtility;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangtao on 16/3/6.
 */
public class ReleaseImp implements ImageLoadingListener {

    private List<Bitmap> mBitmaps;

    public ReleaseImp()
    {
        mBitmaps = new ArrayList<>();
    }

    public void cleanBitmapList() {
        if (mBitmaps.size() > 0)
        {
            for (int i = 0 ; i < mBitmaps.size() ; i++)
            {
                Bitmap bitmap = mBitmaps.get(i);
                if (bitmap != null && !bitmap.isRecycled())
                {
                    HaoUtility.print("bitmap-->" + bitmap + " is clear");
                    bitmap.recycle();
                    bitmap = null;
                }
            }
        }
        mBitmaps.clear();
    }

    @Override
    public void onLoadingStarted(String s, View view) {

    }

    @Override
    public void onLoadingFailed(String s, View view, FailReason failReason) {

    }

    @Override
    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
        mBitmaps.add(bitmap);
    }

    @Override
    public void onLoadingCancelled(String s, View view) {

    }
}
