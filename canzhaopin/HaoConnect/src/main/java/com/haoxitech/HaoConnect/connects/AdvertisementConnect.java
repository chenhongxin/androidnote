package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class AdvertisementConnect extends HaoConnect {


    /**
    * 广告:查看表结构（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestColumns(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("advertisement/columns", params, METHOD_GET, response, context);
    }



    /**
    * 广告:新建
    * @param  params  参数
    *                        device_type         integer                       适用设备：1-手机，2-平板，3-pc,4-通用
    *                        genre               integer             *         广告类型：0-未知，1-首页banner广浩，2-海报， 3-一般广告
    *                        picture             string              *         广告图片
    *                        ad_type             integer                       操作类型：1-打开活动/界面，2-调到当前界面的某一项，3-使用内置webView打开url，4-使用浏览器打开url
    *                        ad_value            string                        操作值，type为1这里为url
    *                        view_count          integer                       浏览人数
    *                        status              integer                       0: 已删除  1: 正常 2: 草稿  3：待审
    *                        user_id             integer                       创建者用户id
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestAdd(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("advertisement/add", params, METHOD_POST, response, context);
    }



    /**
    * 广告:更新
    * @param  params  参数
    *                        id                  int                 *         id
    *                        status              integer                       0: 已删除  1: 正常 2: 草稿  3：待审
    *                        device_type         integer                       适用设备：1-手机，2-平板，3-pc,4-通用
    *                        genre               integer                       广告类型：0-未知，1-首页banner广浩，2-海报， 3-一般广告
    *                        picture             string                        广告图片
    *                        ad_type             integer                       操作类型：1-打开活动/界面，2-调到当前界面的某一项，3-使用内置webView打开url，4-使用浏览器打开url
    *                        ad_value            string                        操作值，type为1这里为url
    *                        view_count          integer                       浏览人数
    *                        user_id             integer                       创建者用户id
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdate(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("advertisement/update", params, METHOD_POST, response, context);
    }



    /**
    * 广告:列表
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        iscountall          bool                          是否统计总数 1是 0否
    *                        order               string                        排序方式
    *                        isreverse           int                           是否倒序 0否 1是
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       广告id
    *                        status              integer                       0: 已删除  1: 正常 2: 草稿  3：待审
    *                        user_id             integer                       创建者用户id
    *                        device_type         integer                       适用设备：1-手机，2-平板，3-pc,4-通用
    *                        genre               integer                       广告类型：0-未知，1-首页banner广浩，2-海报， 3-一般广告
    *                        picture             string                        广告图片
    *                        ad_type             integer                       操作类型：1-打开活动/界面，2-调到当前界面的某一项，3-使用内置webView打开url，4-使用浏览器打开url
    *                        ad_value            string                        操作值，type为1这里为url
    *                        view_count          integer                       浏览人数
    *                        create_timestart    datetime                      >=起始时间（之后）：创建时间
    *                        create_timeend      datetime                      <结束时间（之前）：创建时间
    *                        modify_timestart    datetime                      >=起始时间（之后）：修改时间
    *                        modify_timeend      datetime                      <结束时间（之前）：修改时间
    *                        keyword             string                        检索关键字
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestList(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("advertisement/list", params, METHOD_GET, response, context);
    }



    /**
    * 广告:详情
    * @param  params  参数
    *                        id                  int                 *         id
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestDetail(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("advertisement/detail", params, METHOD_GET, response, context);
    }

}