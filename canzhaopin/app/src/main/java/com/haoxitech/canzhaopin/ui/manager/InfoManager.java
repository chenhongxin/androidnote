package com.haoxitech.canzhaopin.ui.manager;

import com.haoxitech.canzhaopin.R;
import com.haoxitech.canzhaopin.app.AppContext;

/**
 * Created by wangtao on 16/3/21.
 */
public class InfoManager {

    private static String[] educationArray = AppContext.getInstance().getResources().getStringArray(R.array.education_type);

    public static String getEducationByID(String id) {
        return educationArray[Integer.parseInt(id)];
    }
}
