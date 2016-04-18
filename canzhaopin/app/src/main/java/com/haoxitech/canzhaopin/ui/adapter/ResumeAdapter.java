package com.haoxitech.canzhaopin.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseItemAdapter;
import com.haoxitech.canzhaopin.ui.activity.EducationAddActivity;
import com.haoxitech.canzhaopin.ui.activity.ResumeActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by wangtao on 16/3/21.
 */
public class ResumeAdapter extends BaseItemAdapter {

    private int type = 0; // 0 教育 1 工作

    public ResumeAdapter(Context context) {
        super(context);
    }

    public ResumeAdapter(Context context, boolean show, int type) {
        super(context);
        this.showAddView = show;
        this.type = type;
    }

    private boolean showAddView = false;

    public void setShowAddView(boolean show) {

        this.showAddView = show;
    }

    @Override
    public int getCount() {
        if (this.showAddView) {
            return super.getCount() + 1;
        } else
            return super.getCount();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_resume, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        int index;
        if (this.showAddView) {
            if (i == 0) {
                holder.addLayout.setVisibility(View.VISIBLE);
                holder.infoLayout.setVisibility(View.GONE);
                if (type == 0) {
                    holder.addInfoText.setText("说说你的教育经历");
                    holder.addSubInfoText.setText("从最近的教育经历说起");
                    holder.addLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((ResumeActivity)context).addEducation();
                        }
                    });
                } else if (type == 1) {
                    holder.addInfoText.setText("说说你的工作经历");
                    holder.addSubInfoText.setText("从最后一份工作说起");
                }
            } else {
                index = i - 1;
                holder.addLayout.setVisibility(View.GONE);
                holder.infoLayout.setVisibility(View.VISIBLE);
            }
        } else {
            index = i;
        }

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.resume_icon)
        ImageView resumeIcon;
        @InjectView(R.id.time_text)
        TextView timeText;
        @InjectView(R.id.desc_text)
        TextView descText;
        @InjectView(R.id.content_text)
        TextView contentText;
        @InjectView(R.id.info_layout)
        LinearLayout infoLayout;
        @InjectView(R.id.add_info_text)
        TextView addInfoText;
        @InjectView(R.id.add_sub_info_text)
        TextView addSubInfoText;
        @InjectView(R.id.add_layout)
        LinearLayout addLayout;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
