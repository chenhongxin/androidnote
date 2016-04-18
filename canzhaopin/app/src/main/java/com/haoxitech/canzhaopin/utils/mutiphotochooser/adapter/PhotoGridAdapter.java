package com.haoxitech.canzhaopin.utils.mutiphotochooser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseItemAdapter;


/**
 * Created by wangtao on 16/1/29.
 */
public class PhotoGridAdapter extends BaseItemAdapter {
    public PhotoGridAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCount() {
        return super.getCount() + 1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null)
        {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_photo_grid, null);
            holder.imageView = (ImageView) view.findViewById(R.id.content_image);
            holder.deleteBtn = (Button) view.findViewById(R.id.delete_btn);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        Button deleteBtn;
    }
}
