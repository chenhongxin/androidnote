package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class BannerConnect extends HaoConnect {


    /**
    * 轮播图:查看表结构（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestColumns(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("banner/columns", params, METHOD_GET, response, context);
    }



    /**
    * 轮播图:新建
    * @param  params  参数
    *                        title               string              *         轮播图名称
    *                        photo               text                *         照片预览（单个七牛网址）
    *                        ad_type             integer             *         轮播图类型 1招聘详情 20webview 21外跳链接
    *                        ad_value            string              *         轮播图有效值 根据adType不同，此处用途不同，如adType为20此处则为url，如adType为1此处则为套系ID
    *                        user_id             integer                       用户ID
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    *                        status              integer                       0: 不存在  1: 正常 2: 草稿  3：待审
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestAdd(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("banner/add", params, METHOD_POST, response, context);
    }



    /**
    * 轮播图:更新
    * @param  params  参数
    *                        id                  int                 *         id
    *                        title               string                        轮播图名称
    *                        photo               text                          照片预览（单个七牛网址）
    *                        ad_type             integer                       轮播图类型 1招聘详情 20webview 21外跳链接
    *                        ad_value            string                        轮播图有效值 根据adType不同，此处用途不同，如adType为20此处则为url，如adType为1此处则为套系ID
    *                        status              integer                       0: 不存在  1: 正常 2: 草稿  3：待审
    *                        user_id             integer                       用户ID
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdate(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("banner/update", params, METHOD_POST, response, context);
    }



    /**
    * 轮播图:列表
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        iscountall          bool                          是否统计总数 1是 0否
    *                        order               string                        排序方式
    *                        isreverse           int                           是否倒序 0否 1是
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       
    *                        user_id             integer                       用户ID
    *                        title               string                        轮播图名称
    *                        photo               text                          照片预览（单个七牛网址）
    *                        ad_type             integer                       轮播图类型 1招聘详情 20webview 21外跳链接
    *                        ad_value            string                        轮播图有效值 根据adType不同，此处用途不同，如adType为20此处则为url，如adType为1此处则为套系ID
    *                        create_timestart    datetime                      >=起始时间（之后）：创建时间
    *                        create_timeend      datetime                      <结束时间（之前）：创建时间
    *                        modify_timestart    datetime                      >=起始时间（之后）：修改时间
    *                        modify_timeend      datetime                      <结束时间（之前）：修改时间
    *                        status              integer                       0: 不存在  1: 正常 2: 草稿  3：待审
    *                        keyword             string                        检索关键字
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestList(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("banner/list", params, METHOD_GET, response, context);
    }



    /**
    * 轮播图:详情
    * @param  params  参数
    *                        id                  int                 *         id
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestDetail(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("banner/detail", params, METHOD_GET, response, context);
    }

}