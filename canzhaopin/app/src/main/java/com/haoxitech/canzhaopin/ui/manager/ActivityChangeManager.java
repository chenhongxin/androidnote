package com.haoxitech.canzhaopin.ui.manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by wangtao on 16/2/23.
 */
public class ActivityChangeManager {
    public static void changeActivity(Context context, int type, String value) {
        ActivityChangeManager.changeActivity(context, type, value, null);
    }

    public static void changeActivity(Context context, int type, String value, String title) {
        switch (type) {
            case 1: {
//                Intent intent = new Intent(context, ProjectDetailActivity.class);
//                if (title != null) {
//                    intent.putExtra("title", title);
//                }
//                intent.putExtra("id", value);
//                context.startActivity(intent);
            }
            break;
            case 20: {
//                Intent intent = new Intent(context, WebActivity.class);
//                intent.putExtra("content", value);
//                context.startActivity(intent);
            }
            break;
            case 21: {
                try {
                    if (!value.startsWith("http://") && !value.startsWith("https://")) {
                        value = "http://" + value;
                    }
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri content_url = Uri.parse(value);
                    intent.setData(content_url);
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }

//    public static void changeActivity(Context context, HeaderImageResult headerImageResult) {
//        ActivityChangeManager.changeActivity(context, Integer.parseInt(headerImageResult.findAdType() + ""), headerImageResult.findAdValue().toString());
//    }
}
