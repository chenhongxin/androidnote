package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class CrowdfundingConnect extends HaoConnect {


    /**
    * 理想众筹:查看表结构（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestColumns(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("crowdfunding/columns", params, METHOD_GET, response, context);
    }



    /**
    * 理想众筹:新建
    * @param  params  参数
    *                        crowdfunding_type_idinteger             *         众筹类型id
    *                        title               string              *         众筹标题
    *                        picture             string                        众筹图片
    *                        voice               string                        众筹语音
    *                        voice_length        integer                       语音时长，单位秒
    *                        content             text                *         众筹项目内容
    *                        process             integer                       项目进程：0-未开始，1-进行中，2-已成功，3-已失败
    *                        start_time          datetime                      开始时间
    *                        end_time            datetime                      结束时间
    *                        assistance_count    integer                       援助人数
    *                        view_count          integer                       浏览人数
    *                        likes_count         integer                       赞的人数
    *                        status              integer                       状态：0-已删除，1-正常，2-草稿，3-待审
    *                        user_id             integer                       创建者用户id
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestAdd(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("crowdfunding/add", params, METHOD_POST, response, context);
    }



    /**
    * 理想众筹:更新
    * @param  params  参数
    *                        id                  int                 *         id
    *                        status              integer                       状态：0-已删除，1-正常，2-草稿，3-待审
    *                        crowdfunding_type_idinteger                       众筹类型id
    *                        title               string                        众筹标题
    *                        picture             string                        众筹图片
    *                        voice               string                        众筹语音
    *                        voice_length        integer                       语音时长，单位秒
    *                        content             text                          众筹项目内容
    *                        process             integer                       项目进程：0-未开始，1-进行中，2-已成功，3-已失败
    *                        start_time          datetime                      开始时间
    *                        end_time            datetime                      结束时间
    *                        assistance_count    integer                       援助人数
    *                        view_count          integer                       浏览人数
    *                        likes_count         integer                       赞的人数
    *                        user_id             integer                       创建者用户id
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdate(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("crowdfunding/update", params, METHOD_POST, response, context);
    }



    /**
    * 理想众筹:列表
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        iscountall          bool                          是否统计总数 1是 0否
    *                        order               string                        排序方式
    *                        isreverse           int                           是否倒序 0否 1是
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       众筹id
    *                        status              integer                       状态：0-已删除，1-正常，2-草稿，3-待审
    *                        user_id             integer                       创建者用户id
    *                        crowdfunding_type_idinteger                       众筹类型id
    *                        title               string                        众筹标题
    *                        picture             string                        众筹图片
    *                        voice               string                        众筹语音
    *                        voice_length        integer                       语音时长，单位秒
    *                        content             text                          众筹项目内容
    *                        process             integer                       项目进程：0-未开始，1-进行中，2-已成功，3-已失败
    *                        start_timestart     datetime                      >=起始时间（之后）：开始时间
    *                        start_timeend       datetime                      <结束时间（之前）：开始时间
    *                        end_timestart       datetime                      >=起始时间（之后）：结束时间
    *                        end_timeend         datetime                      <结束时间（之前）：结束时间
    *                        assistance_count    integer                       援助人数
    *                        view_count          integer                       浏览人数
    *                        likes_count         integer                       赞的人数
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
        return request("crowdfunding/list", params, METHOD_GET, response, context);
    }



    /**
    * 理想众筹:详情
    * @param  params  参数
    *                        id                  int                 *         id
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestDetail(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("crowdfunding/detail", params, METHOD_GET, response, context);
    }

}