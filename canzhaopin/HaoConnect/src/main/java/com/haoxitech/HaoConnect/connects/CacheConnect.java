package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class CacheConnect extends HaoConnect {


    /**
    * 缓存:清空所有缓存（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestEmptyCache(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("cache/emptyCache", params, METHOD_POST, response, context);
    }



    /**
    * 缓存:缓存服务器状态（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestInfo(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("cache/info", params, METHOD_GET, response, context);
    }

}