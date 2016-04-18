package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class DeliveryLogConnect extends HaoConnect {


    /**
    * 投递简历操作记录:查看表结构（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestColumns(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("delivery_log/columns", params, METHOD_GET, response, context);
    }



    /**
    * 投递简历操作记录:新建
    * @param  params  参数
    *                        look_user_id        integer             *         查看人ID
    *                        resume_id           integer             *         简历ID
    *                        edit_status         integer             *         1.查看  2.邀请面试  3.拒绝
    *                        user_id             integer                       
    *                        status              integer                       0: 已删除  1: 正常
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestAdd(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("delivery_log/add", params, METHOD_POST, response, context);
    }



    /**
    * 投递简历操作记录:更新
    * @param  params  参数
    *                        id                  int                 *         id
    *                        look_user_id        integer                       查看人ID
    *                        resume_id           integer                       简历ID
    *                        edit_status         integer                       1.查看  2.邀请面试  3.拒绝
    *                        status              integer                       0: 已删除  1: 正常
    *                        user_id             integer                       
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdate(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("delivery_log/update", params, METHOD_POST, response, context);
    }



    /**
    * 投递简历操作记录:列表
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        iscountall          bool                          是否统计总数 1是 0否
    *                        order               string                        排序方式
    *                        isreverse           int                           是否倒序 0否 1是
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       
    *                        user_id             integer                       
    *                        look_user_id        integer                       查看人ID
    *                        resume_id           integer                       简历ID
    *                        edit_status         integer                       1.查看  2.邀请面试  3.拒绝
    *                        status              integer                       0: 已删除  1: 正常
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
        return request("delivery_log/list", params, METHOD_GET, response, context);
    }



    /**
    * 投递简历操作记录:详情
    * @param  params  参数
    *                        id                  int                 *         id
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestDetail(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("delivery_log/detail", params, METHOD_GET, response, context);
    }

}