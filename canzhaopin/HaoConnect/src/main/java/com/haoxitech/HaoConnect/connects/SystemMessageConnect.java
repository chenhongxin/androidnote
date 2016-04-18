package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class SystemMessageConnect extends HaoConnect {


    /**
    * 残招聘-系统消息:查看表结构（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestColumns(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("system_message/columns", params, METHOD_GET, response, context);
    }



    /**
    * 残招聘-系统消息:新建
    * @param  params  参数
    *                        receive             integer             *         消息接收人
    *                        title               string              *         
    *                        content             string              *         消息内容
    *                        user_id             integer                       
    *                        status              integer                       0: 已删除  1: 正常 2: 待审  3：拒绝
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestAdd(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("system_message/add", params, METHOD_POST, response, context);
    }



    /**
    * 残招聘-系统消息:更新
    * @param  params  参数
    *                        id                  int                 *         id
    *                        receive             integer                       消息接收人
    *                        title               string                        
    *                        content             string                        消息内容
    *                        status              integer                       0: 已删除  1: 正常 2: 待审  3：拒绝
    *                        user_id             integer                       
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdate(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("system_message/update", params, METHOD_POST, response, context);
    }



    /**
    * 残招聘-系统消息:列表
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        iscountall          bool                          是否统计总数 1是 0否
    *                        order               string                        排序方式
    *                        isreverse           int                           是否倒序 0否 1是
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       
    *                        user_id             integer                       
    *                        receive             integer                       消息接收人
    *                        title               string                        
    *                        content             string                        消息内容
    *                        status              integer                       0: 已删除  1: 正常 2: 待审  3：拒绝
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
        return request("system_message/list", params, METHOD_GET, response, context);
    }



    /**
    * 残招聘-系统消息:详情
    * @param  params  参数
    *                        id                  int                 *         id
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestDetail(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("system_message/detail", params, METHOD_GET, response, context);
    }

}