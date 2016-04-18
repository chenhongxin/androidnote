package com.haoxitech.canzhaopin.app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.widget.CheckBox;

import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoUtility;
import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.base.BaseApplication;
import com.haoxitech.canzhaopin.database.DataBaseHelper;
import com.haoxitech.canzhaopin.ui.activity.MainActivity;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangtao on 15/11/17.
 */
public class AppContext extends BaseApplication {
    private static AppContext instance;

    public String userId = "";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initImageLoader();
//        initUmeng();
        dealXGPush();
        HaoConnect.init(this, AppContext.getInstance().getVersionName());

        initDataBase();
    }

    public void initDataBase() {
        try {
            DataBaseHelper.copyDataBase(this, "zipArea.db");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static AppContext getInstance() {
        return instance;
    }

    public String getUserId() {
        if (userId == null || userId.equals("")) {
            try {
                userId = HaoConnect.getString("userID");
            } catch (Exception e) {

            }
        }
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 初始化图片加载类
     */
    private void initImageLoader() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)// 防止内存溢出的，图片太多就这这个。还有其他设置
                        // 如Bitmap.Config.ARGB_8888
                .showImageOnLoading(R.drawable.image_default) // 默认图片
                .showImageForEmptyUri(R.drawable.image_default) //
                        // url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.image_default)// 加载失败显示的图片
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).memoryCacheExtraOptions(480, 800)
                // 缓存在内存的图片的宽和高度
//                .discCacheExtraOptions(480, 800, CompressFormat.PNG, 70, null)
                        // CompressFormat.PNG类型，70质量（0-100）
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024) // 缓存到内存的最大数据
                .discCacheSize(50 * 1024 * 1024). // 缓存到文件的最大数据
                        discCacheFileCount(1000) // 文件数量
                .defaultDisplayImageOptions(options). // 上面的options对象，一些属性配置
                        build();
        ImageLoader.getInstance().init(config); // 初始化
    }

    private void dealXGPush() {
        if (isMainProcess()) {
            // 为保证弹出通知前一定调用本方法，需要在application的onCreate注册
            // 收到通知时，会调用本回调函数。
            // 相当于这个回调会拦截在信鸽的弹出通知之前被截取
            // 一般上针对需要获取通知内容、标题，设置通知点击的跳转逻辑等等
//            XGPushManager
//                    .setNotifactionCallback(new XGPushNotifactionCallback() {
//
//                        @Override
//                        public void handleNotify(XGNotifaction xGNotifaction) {
//
//                            // 获取标签、内容、自定义内容
//                            String title = xGNotifaction.getTitle();
//                            String content = xGNotifaction.getContent();
//                            String customContent = xGNotifaction
//                                    .getCustomContent();
//                            // 其它的处理
//                            // 如果还要弹出通知，可直接调用以下代码或自己创建Notifaction，否则，本通知将不会弹出在通知栏中。
//                            xGNotifaction.doNotify();
//                        }
//                    });
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initUmeng() {
//        PlatformConfig.setWeixin(Constant.WXAPPID, Constant.WXAPPKEY);
//        //微信 appid appsecret
//        PlatformConfig.setSinaWeibo(Constant.SINAAPPID, Constant.SINAAPPKEY);
//        //新浪微博 appkey appsecret
//        PlatformConfig.setQQZone(Constant.QQAPPID, Constant.QQAPPKEY);
//         QQ和Qzone appid appkey

        HaoUtility.print("getDeviceInfo-->" + getDeviceInfo(this));
    }


    @SuppressLint("NewApi")
    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                device_id = tm.getDeviceId();
            }
            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);
            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
