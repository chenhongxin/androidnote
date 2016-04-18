package com.haoxitech.canzhaopin.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.haoxitech.HaoConnect.HaoUtility;

import java.util.ArrayList;

/**
 * Created by wangtao on 15/11/17.
 */
public abstract class BaseItemAdapter<T> extends BaseAdapter {

    public Context context;

    public ArrayList<T> dataList = null;

    public BaseItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<T> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public ArrayList<T> getData() {
        return this.dataList;
    }

    public void addData(ArrayList<T> addList) {
        if (this.dataList == null) {
            setData(addList);
        } else {
            if(addList.size()==0){
                return;
            }
            dataList.addAll(addList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public T getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

}
