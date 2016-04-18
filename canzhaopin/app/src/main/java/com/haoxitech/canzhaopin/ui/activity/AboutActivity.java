package com.haoxitech.canzhaopin.ui.activity;

import android.webkit.WebView;

import com.haoxitech.HaoConnect.HaoConfig;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseTitleActivity;

/**
 * Created by wangtao on 16/2/25.
 */
public class AboutActivity extends BaseTitleActivity {

    private WebView webView;

    @Override
    public int getContentViewID() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("关于我们");

        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("http://" + HaoConfig.getApiHost() + "/abort.php");
    }
}
