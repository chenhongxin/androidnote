package com.haoxitech.canzhaopin.ui.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.haoxitech.HaoConnect.results.BannerResult;
import com.haoxitech.canzhaopin.view.AdvImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Guojie on 2016/2/2.
 */
public class ADVPagerAdapter extends PagerAdapter {

    private Activity activity;
    private ArrayList listResults;

    public ADVPagerAdapter(Activity activity, ArrayList listResults) {
        this.activity = activity;
        this.listResults = listResults;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        AdvImageView imageView = new AdvImageView(activity);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        try {
            BannerResult headerImageResult = (BannerResult) listResults.get(position);
            imageView.setOnClickListener(new ImageItemOnClick(headerImageResult.findAsString("id").toString(), position));
            ImageLoader.getInstance().displayImage(headerImageResult.findAsString("photo").toString(), imageView);
        } catch (Exception e) {

        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return listResults.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 轮播图点击事件
     */
    class ImageItemOnClick implements View.OnClickListener {
        private String id;
        private int position;

        ImageItemOnClick(String id, int position) {
            this.id = id;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
//            HeaderImageResult headerImageResult = (HeaderImageResult) listResults.get(position);
//            ActivityChangeManager.changeActivity(activity, Integer.parseInt(headerImageResult.findAdType() + ""), headerImageResult.findAdValue().toString(), headerImageResult.findTitle().toString());
        }
    }
}