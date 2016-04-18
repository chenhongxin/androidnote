package com.haoxitech.canzhaopin.database;

import android.util.Log;

import java.util.List;
import java.util.Map;

/**
 * Created by wangtao on 16/3/13.
 */
public class DBHelper extends DataBaseHelper {
    public static List<Map<String, Object>> query(String[] fields, String tbName, String[] params) {
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        if (fields == null || fields.length == 0) {
            sql.append("*");
        } else {
            for (int i = 0; i < fields.length; i++) {
                sql.append("" + fields[i]);
                if (i != fields.length - 1) {
                    sql.append(",");
                }
            }
        }

        sql.append(" from " + tbName);

        if (params != null && params.length > 0 ) {
            sql.append(" where ");
            for (int i = 0; i < params.length; i++) {
                sql.append("" + params[i]);
                if (i != params.length - 1) {
                    sql.append(" and ");
                }
            }
        }
        Log.d("tag", sql.toString());
        return select(sql.toString());
    }
}
