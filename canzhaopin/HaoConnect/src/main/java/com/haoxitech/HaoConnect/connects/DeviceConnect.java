package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class DeviceConnect extends HaoConnect {


    /**
    * 登录设备:查看表结构（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestColumns(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("device/columns", params, METHOD_GET, response, context);
    }



    /**
    * 登录设备:新建
    * @param  params  参数
    *                        device_token        string              *         设备token 友盟或iOS自带
    *                        device_type         integer             *         1：浏览器设备 2：pc设备 3：Android设备 4：ios设备 5：windows phone设备
    *                        device_info         string              *         设备名称 如iPhone6Plus
    *                        client_info         string              *         应用识别信息
    *                        client_version      string              *         设备应用版本号
    *                        is_push_allowed     integer             *         是否允许推送 1允许 2不允许
    *                        deny_push_types     string              *         推送禁止设定（逗号分隔、默认全推）
    *                        last_login_time     datetime            *         最后一次登录时间
    *                        deploy_status       integer             *         推送模式（iOS）,1是开发模式  2是正式环境
    *                        is_sound            integer             *         声音：1开 0关
    *                        is_shake            integer             *         震动：1开 0关
    *                        user_id             integer                       用户ID
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestAdd(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("device/add", params, METHOD_POST, response, context);
    }



    /**
    * 登录设备:绑定设备
    * @param  params  参数
    *                        devicetoken         string              *         设备token 友盟或iOS自带
    *                        deviceinfo          string              *         设备名称 如iPhone6Plus
    *                        deploystatus        string              *         推送模式（iOS）,1是开发模式  2是正式环境
    *                        denypushtypes       string              *         推送禁止设定（逗号分隔、默认全推）
    *                        ispushallowed       string              *         密码
    *                        issound             string              *         声音：1开 0关
    *                        isshake             string              *         震动：1开 0关
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdate(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("device/update", params, METHOD_POST, response, context);
    }



    /**
    * 登录设备:列表
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        iscountall          bool                          是否统计总数 1是 0否
    *                        order               string                        排序方式
    *                        isreverse           int                           是否倒序 0否 1是
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       
    *                        user_id             integer                       用户ID
    *                        device_token        string                        设备token 友盟或iOS自带
    *                        device_type         integer                       1：浏览器设备 2：pc设备 3：Android设备 4：ios设备 5：windows phone设备
    *                        device_info         string                        设备名称 如iPhone6Plus
    *                        client_info         string                        应用识别信息
    *                        client_version      string                        设备应用版本号
    *                        is_push_allowed     integer                       是否允许推送 1允许 2不允许
    *                        deny_push_types     string                        推送禁止设定（逗号分隔、默认全推）
    *                        create_timestart    datetime                      >=起始时间（之后）：创建时间
    *                        create_timeend      datetime                      <结束时间（之前）：创建时间
    *                        modify_timestart    datetime                      >=起始时间（之后）：修改时间
    *                        modify_timeend      datetime                      <结束时间（之前）：修改时间
    *                        last_login_timestartdatetime                      >=起始时间（之后）：最后一次登录时间
    *                        last_login_timeend  datetime                      <结束时间（之前）：最后一次登录时间
    *                        deploy_status       integer                       推送模式（iOS）,1是开发模式  2是正式环境
    *                        is_sound            integer                       声音：1开 0关
    *                        is_shake            integer                       震动：1开 0关
    *                        keyword             string                        检索关键字
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestList(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("device/list", params, METHOD_GET, response, context);
    }



    /**
    * 登录设备:详情
    * @param  params  参数
    *                        id                  int                 *         id
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestDetail(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("device/detail", params, METHOD_GET, response, context);
    }

}