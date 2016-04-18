package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class ZipAreaConnect extends HaoConnect {


    /**
    * 地区:查看表结构（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestColumns(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("zip_area/columns", params, METHOD_GET, response, context);
    }



    /**
    * 地区:新建
    * @param  params  参数
    *                        area_name           string              *         
    *                        area_parent         integer             *         父级区域
    *                        area_main           integer             *         一级区域
    *                        area_second         integer             *         二级区域
    *                        area_third          integer             *         三级区域
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestAdd(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("zip_area/add", params, METHOD_POST, response, context);
    }



    /**
    * 地区:列表
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        iscountall          bool                          是否统计总数 1是 0否
    *                        order               string                        排序方式
    *                        isreverse           int                           是否倒序 0否 1是
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       
    *                        area_name           string                        
    *                        area_parent         integer                       父级区域
    *                        area_main           integer                       一级区域
    *                        area_second         integer                       二级区域
    *                        area_third          integer                       三级区域
    *                        keyword             string                        检索关键字
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestList(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("zip_area/list", params, METHOD_GET, response, context);
    }

}