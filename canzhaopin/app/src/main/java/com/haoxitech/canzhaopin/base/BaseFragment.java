package com.haoxitech.canzhaopin.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haoxitech.canzhaopin.view.CustomProgressDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangtao on 16/1/19.
 */
public abstract class BaseFragment extends Fragment {

    protected Map<String, Object> params = new HashMap<>();

    protected CustomProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getFragmentLayoutId(),null);

        progressDialog = new CustomProgressDialog(getActivity());

        initView(view);
        return view;
    }

    public abstract void initView(View view);

    public abstract int getFragmentLayoutId();

    @Override
    public void onResume() {
        super.onResume();

//        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    public void showToast(String notice) {
        try {
            Toast.makeText(getActivity(), notice, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
