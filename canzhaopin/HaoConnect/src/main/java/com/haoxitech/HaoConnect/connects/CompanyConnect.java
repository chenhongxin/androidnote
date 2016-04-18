package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class CompanyConnect extends HaoConnect {


    /**
    * 公司:查看表结构（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestColumns(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("company/columns", params, METHOD_GET, response, context);
    }



    /**
    * 公司:新建
    * @param  params  参数
    *                        title               string              *         公司名称
    *                        address             string              *         公司地址
    *                        zip_area_id         integer             *         地址ID
    *                        industry_id         integer             *         行业
    *                        nature              integer             *         1.国有企业  2.集体所有制  3.联营企业  4.三资企业   5.私营企业
    *                        scale               integer             *         公司规模  1.10人以下   2.10-30人   3.30-100人   4.100人以上
    *                        company_desc        text                *         企业介绍
    *                        permit              string              *         营业执照
    *                        user_id             integer                       创建人
    *                        status              integer                       0: 已删除  1: 正常 2: 待审  3：拒绝
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestAdd(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("company/add", params, METHOD_POST, response, context);
    }



    /**
    * 公司:更新
    * @param  params  参数
    *                        id                  int                 *         id
    *                        title               string                        公司名称
    *                        address             string                        公司地址
    *                        zip_area_id         integer                       地址ID
    *                        industry_id         integer                       行业
    *                        nature              integer                       1.国有企业  2.集体所有制  3.联营企业  4.三资企业   5.私营企业
    *                        scale               integer                       公司规模  1.10人以下   2.10-30人   3.30-100人   4.100人以上
    *                        company_desc        text                          企业介绍
    *                        permit              string                        营业执照
    *                        status              integer                       0: 已删除  1: 正常 2: 待审  3：拒绝
    *                        user_id             integer                       创建人
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdate(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("company/update", params, METHOD_POST, response, context);
    }



    /**
    * 公司:列表
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        iscountall          bool                          是否统计总数 1是 0否
    *                        order               string                        排序方式
    *                        isreverse           int                           是否倒序 0否 1是
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       
    *                        user_id             integer                       创建人
    *                        title               string                        公司名称
    *                        address             string                        公司地址
    *                        zip_area_id         integer                       地址ID
    *                        industry_id         integer                       行业
    *                        nature              integer                       1.国有企业  2.集体所有制  3.联营企业  4.三资企业   5.私营企业
    *                        scale               integer                       公司规模  1.10人以下   2.10-30人   3.30-100人   4.100人以上
    *                        company_desc        text                          企业介绍
    *                        permit              string                        营业执照
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
        return request("company/list", params, METHOD_GET, response, context);
    }



    /**
    * 公司:详情
    * @param  params  参数
    *                        id                  int                 *         id
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestDetail(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("company/detail", params, METHOD_GET, response, context);
    }

}