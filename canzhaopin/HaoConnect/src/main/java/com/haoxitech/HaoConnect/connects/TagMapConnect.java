package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class TagMapConnect extends HaoConnect {


    /**
    * 标签:标签映射●查看表结构（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestColumns(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("tag_map/columns", params, METHOD_GET, response, context);
    }



    /**
    * 标签:标签映射●新建
    * @param  params  参数
    *                        userid              integer             *         创建用户id
    *                        tag_id              integer             *         标签id
    *                        map_type            integer             *         0-未知，1-志愿者标签，2-劳动项目，3-群标签
    *                        target_id           integer             *         具有该标签的对象id，由mapType指定类型
    *                        status              integer                       0: 已删除  1: 正常 2: 草稿  3：待审
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestAdd(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("tag_map/add", params, METHOD_POST, response, context);
    }



    /**
    * 标签:标签映射●更新
    * @param  params  参数
    *                        id                  int                 *         id
    *                        status              integer                       0: 已删除  1: 正常 2: 草稿  3：待审
    *                        userid              integer                       创建用户id
    *                        tag_id              integer                       标签id
    *                        map_type            integer                       0-未知，1-志愿者标签，2-劳动项目，3-群标签
    *                        target_id           integer                       具有该标签的对象id，由mapType指定类型
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdate(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("tag_map/update", params, METHOD_POST, response, context);
    }



    /**
    * 标签:标签映射●列表
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        iscountall          bool                          是否统计总数 1是 0否
    *                        order               string                        排序方式
    *                        isreverse           int                           是否倒序 0否 1是
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       标签映射id
    *                        status              integer                       0: 已删除  1: 正常 2: 草稿  3：待审
    *                        userid              integer                       创建用户id
    *                        tag_id              integer                       标签id
    *                        map_type            integer                       0-未知，1-志愿者标签，2-劳动项目，3-群标签
    *                        target_id           integer                       具有该标签的对象id，由mapType指定类型
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
        return request("tag_map/list", params, METHOD_GET, response, context);
    }



    /**
    * 标签:标签映射●详情
    * @param  params  参数
    *                        id                  int                 *         id
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestDetail(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("tag_map/detail", params, METHOD_GET, response, context);
    }

}