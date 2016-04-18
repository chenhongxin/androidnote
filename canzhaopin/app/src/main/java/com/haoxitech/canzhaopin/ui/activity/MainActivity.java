package com.haoxitech.canzhaopin.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.app.AppContext;
import com.haoxitech.canzhaopin.common.RequestCommon;
import com.haoxitech.canzhaopin.ui.fragment.HomeFragment;
import com.haoxitech.canzhaopin.ui.fragment.MessageFragment;
import com.haoxitech.canzhaopin.ui.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangtao on 16/3/15.
 */
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;

    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;
    private int tabCheckedId = 0;
//    public static MainEnum mainEnum = MainEnum.loginsuccess;
    List<Fragment> fragments = new ArrayList<Fragment>();
    RadioButton home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragments.add(fm.findFragmentById(R.id.f_index_home));
        fragments.add(fm.findFragmentById(R.id.f_index_message));
        fragments.add(fm.findFragmentById(R.id.f_index_message));
        fragments.add(fm.findFragmentById(R.id.f_index_mine));
        for(Fragment fragment : fragments){
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.show(fragments.get(0)).commit();
        initView();
//        IntentFilter intentFilter = new IntentFilter("com.haoxitech.mainactivity");
//        registerReceiver(new MainBroadCast(), intentFilter);
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);
        tabCheckedId = R.id.home_btn;
        home_btn = (RadioButton) findViewById(R.id.home_btn);
        home_btn.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        fragmentTransaction = fm.beginTransaction();
        for(Fragment fragment : fragments){
            fragmentTransaction.hide(fragment);
        }
        switch (i) {
            case R.id.home_btn: {
                fragmentTransaction.show(fragments.get(0)).commit();
            }
            break;
            case R.id.message_btn: {
                fragmentTransaction.show(fragments.get(1)).commit();
            }
            break;
            case R.id.activity_btn: {
                fragmentTransaction.show(fragments.get(2)).commit();
            }
            break;
            case R.id.mine_btn: {
                if (AppContext.getInstance().getUserId() == null || AppContext.getInstance().getUserId().equals("")) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent, RequestCommon.REQUEST_LOGIN_ID);
                    radioGroup.check(tabCheckedId);
                    return;
                }
                fragmentTransaction.show(fragments.get(3)).commit();
            }
            break;
        }
        this.tabCheckedId = i;
    }

    /**
     * 进行Fragment的替换
     */
    public void replaceFragment(Fragment fragment) {
        try {
            if (!fragment.isAdded()) {
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
//                transaction.replace(R.id.fragment, fragment);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCommon.REQUEST_LOGIN_ID: {
                    radioGroup.check(R.id.mine_btn);
                }
                break;
                case RequestCommon.REQUEST_LOGIN_BACK:
                    radioGroup.check(R.id.home_btn);
                    break;
            }
        }
    }

//    class MainBroadCast extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if(mainEnum == MainEnum.loginsuccess){
////                home_btn.setChecked(true);
//                radioGroup.check(R.id.home_btn);
//            }
//        }
//    }

//    enum MainEnum{
//        loginsuccess
//    }

}
