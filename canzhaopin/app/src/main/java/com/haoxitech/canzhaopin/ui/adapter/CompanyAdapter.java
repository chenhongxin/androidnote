package com.haoxitech.canzhaopin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoxitech.HaoConnect.results.CompanyResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseItemAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wangtao on 16/3/16.
 */
public class CompanyAdapter extends BaseItemAdapter {

    private String[] scaleArray;

    public CompanyAdapter(Context context) {
        super(context);
        scaleArray = context.getResources().getStringArray(R.array.company_scale);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.adapter_company, null);
            holder.companyImage = (ImageView) view.findViewById(R.id.company_image);
            holder.companyNameText = (TextView) view.findViewById(R.id.company_name_text);
            holder.companyAddressText = (TextView) view.findViewById(R.id.address_text);
            holder.companyDescText = (TextView) view.findViewById(R.id.desc_text);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        CompanyResult companyResult = (CompanyResult) dataList.get(i);
        ImageLoader.getInstance().displayImage(companyResult.findLogo().toString(), holder.companyImage);
        holder.companyNameText.setText(companyResult.findTitle().toString());
        holder.companyAddressText.setText(companyResult.find("zipAreaLabel").toString());
        holder.companyDescText.setText(scaleArray[Integer.parseInt(companyResult.find("scale") + "")] + "/" + companyResult.find("industryLabel"));
        return view;
    }

    static class ViewHolder {
        ImageView companyImage;
        TextView companyNameText;
        TextView companyAddressText;
        TextView companyDescText;
    }
}
