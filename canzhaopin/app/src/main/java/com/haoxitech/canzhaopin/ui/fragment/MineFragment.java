package com.haoxitech.canzhaopin.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.haoxitech.HaoConnect.HaoResult;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.haoxitech.HaoConnect.connects.UserConnect;
import com.haoxitech.HaoConnect.results.UserResult;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleFragment;
import com.haoxitech.canzhaopin.common.RequestCommon;
import com.haoxitech.canzhaopin.ui.activity.CollectionActivity;
import com.haoxitech.canzhaopin.ui.activity.ResumeActivity;
import com.haoxitech.canzhaopin.ui.activity.JobSendActivity;
import com.haoxitech.canzhaopin.ui.activity.SettingActivity;
import com.haoxitech.canzhaopin.ui.activity.UserInfoActivity;
import com.haoxitech.canzhaopin.view.CircleImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by wangtao on 16/3/17.
 */
public class MineFragment extends BaseTitleFragment implements View.OnClickListener {

    private TextView nameText;
    private CircleImageView headImage;
    private ImageButton editBtn;

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {
//        super.initView(view);
        view.findViewById(R.id.resume_layout).setOnClickListener(this);
        view.findViewById(R.id.send_layout).setOnClickListener(this);
        view.findViewById(R.id.collection_layout).setOnClickListener(this);
        view.findViewById(R.id.setting_layout).setOnClickListener(this);

        nameText = (TextView) view.findViewById(R.id.name_text);
        headImage = (CircleImageView) view.findViewById(R.id.head_image);
        headImage.setIsCircle(true);

        editBtn = (ImageButton) view.findViewById(R.id.btn_edit);
        editBtn.setOnClickListener(this);

        loadData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.resume_layout: {
                Intent intent = new Intent(getActivity(), ResumeActivity.class);
                startActivity(intent);
            }
                break;
            case R.id.send_layout: {
                Intent intent = new Intent(getActivity(), JobSendActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.collection_layout: {
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.setting_layout: {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent, RequestCommon.REQUEST_LOGIN_BACK);
            }
            break;
            case R.id.btn_edit: {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

    private void loadData() {
        UserConnect.requestGetMyDetail(null, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    UserResult userResult = (UserResult) result;
                    ImageLoader.getInstance().displayImage(userResult.findAvatar().toString(), headImage);
                    nameText.setText(userResult.findNickname().toString());
                }
            }

            @Override
            public void onFail(HaoResult result) {
                super.onFail(result);
                showToast(result.errorStr);
            }
        }, getActivity());
    }
}
