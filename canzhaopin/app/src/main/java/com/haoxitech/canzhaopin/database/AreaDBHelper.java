package com.haoxitech.canzhaopin.database;

import java.util.List;
import java.util.Map;

/**
 * Created by wangtao on 16/3/14.
 */
public class AreaDBHelper {

    private static String tbName = "zipArea";

    public static List<Map<String, Object>> chooseAreaInfo(String parentID) {
        return DBHelper.query(new String[]{"areaName, id"}, tbName, new String[]{"areaParent = " + parentID});
    }

}
