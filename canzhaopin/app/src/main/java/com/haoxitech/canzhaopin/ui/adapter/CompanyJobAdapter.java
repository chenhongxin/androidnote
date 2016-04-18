package com.haoxitech.canzhaopin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoxitech.HaoConnect.results.JobResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseItemAdapter;
import com.haoxitech.canzhaopin.database.DBHelper;
import com.haoxitech.canzhaopin.utils.DateUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wangtao on 16/3/18.
 */
public class CompanyJobAdapter extends BaseItemAdapter {

    private StringBuffer str = new StringBuffer();
    private String[] educationArray;

    public CompanyJobAdapter(Context context) {
        super(context);
        educationArray = context.getResources().getStringArray(R.array.education_type);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_company_job, null);
            holder.titleText = (TextView) view.findViewById(R.id.job_title_text);
            holder.timeText = (TextView) view.findViewById(R.id.time_text);
            holder.companyText = (TextView) view.findViewById(R.id.company_text);
            holder.salaryText = (TextView) view.findViewById(R.id.salary_text);
            holder.descText = (TextView) view.findViewById(R.id.desc_text);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        try {
            JobResult jobResult = (JobResult) dataList.get(i);

            holder.titleText.setText(jobResult.findTitle().toString());
            holder.timeText.setText(DateUtils.formatDisplayTime(jobResult.findCreateTime().toString()));
            holder.companyText.setText(jobResult.find("companyLocal>title").toString());
            if (jobResult.find("salaryType").equals("1")) {
                holder.salaryText.setText(jobResult.find("salaryLabel").toString());
            } else {
                holder.salaryText.setText("面议");
            }

            if (str.length() > 0)
                str.delete(0, str.length());
            str.append(DBHelper.query(new String[]{"areaName"}, "zipArea", new String[]{"id=" + jobResult.find("companyLocal>zipAreaID")}).get(0).get("areaName"));
            str.append(" " + educationArray[Integer.parseInt("" + jobResult.find("educational"))]);
            switch (Integer.parseInt("" + jobResult.find("genre")) ) {
                case 1: {
                    str.append(" 全职");
                }
                break;
                case 2: {
                    str.append(" 兼职");
                }
                break;
                case 3: {
                    str.append(" 实习");
                }
                break;
            }
            str.append(" " + jobResult.find("welfaresLabel"));
            holder.descText.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    static class ViewHolder {
        TextView titleText;
        TextView timeText;
        TextView companyText;
        TextView salaryText;
        TextView descText;
    }
}
