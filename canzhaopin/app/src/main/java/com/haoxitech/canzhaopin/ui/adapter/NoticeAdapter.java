package com.haoxitech.canzhaopin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoxitech.HaoConnect.results.MessageLogResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseItemAdapter;
import com.haoxitech.canzhaopin.utils.DateUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangtao on 16/3/20.
 */
public class NoticeAdapter extends BaseItemAdapter {
    public NoticeAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_notice, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        try {
            MessageLogResult logResult = (MessageLogResult) dataList.get(i);
            holder.timeText.setText(DateUtils.formatDisplayTime(logResult.findCreateTime().toString()));
            holder.contentText.setText(logResult.findContent().toString());
        } catch (Exception e) {

        }

        return null;
    }

    static class ViewHolder {
        @InjectView(R.id.notice_image)
        ImageView noticeImage;
        @InjectView(R.id.time_text)
        TextView timeText;
        @InjectView(R.id.content_text)
        TextView contentText;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
