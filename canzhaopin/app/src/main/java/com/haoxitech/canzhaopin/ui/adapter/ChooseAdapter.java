package com.haoxitech.canzhaopin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseItemAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangtao on 16/3/21.
 */
public class ChooseAdapter extends BaseItemAdapter {

    public ChooseAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_choose, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.chooseText.setText(dataList.get(i).toString());
        return view;
    }


    static class ViewHolder {
        @InjectView(R.id.choose_text)
        TextView chooseText;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
