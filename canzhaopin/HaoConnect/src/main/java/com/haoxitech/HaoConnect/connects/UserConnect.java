package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import com.google.gson.JsonObject;
import com.haoxitech.HaoConnect.HaoResult;
import java.util.Map;
import android.content.Context;

public class UserConnect extends HaoConnect {


    /**
    * 用户:查看表结构（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestColumns(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/columns", params, METHOD_GET, response, context);
    }



    /**
    * 用户:新建
     * @param  params  参数
     *                        username            string                        用户名
     *                        telephone           string              *         用户手机号
     *                        email               string                        邮箱
     *                        password            md5                 *         用户密码
     *                        verify_code         string              *         验证码(残疾人招聘用)
     *                        genre               integer                       用户类型：1-爱心人士，2-机构
     *                        is_disabled         integer                       是否是残障人士：0-否，1-是
     *                        is_volunteer        integer                       是否是志愿者：0-否，1-是
     *                        avatar              string                        头像
     *                        name                string                        姓名
     *                        nickname            string                        昵称
     *                        occupation          string                        职业
     *                        age                 integer                       年龄
     *                        birthday            date                          生日、出生年月日
     *                        sex                 integer                       性别：0-未知，1-男，2-女
     *                        contact             string                        联系方式
     *                        profile             string                        个人介绍
     *                        area_id             integer                       地区id
    *                        town                string                        村镇
    *                        detail              string                        详细地址
    *                        last_longitude      float                         最后一次经度
    *                        last_latitude       float                         最后一次维度
    *                        last_login_time     datetime                      最后登录时间
    *                        last_password_time  datetime                      最后一次密码修改时间
    *                        status              integer                       0: 已删除  1: 正常 2: 草稿  3：待审
    *                        level               integer                       用户级别：0-未激活用户 1-普通用户 5-普通管理员  9-超级管理员
    *                        create_time         datetime                      创建时间
    *                        modify_time         datetime                      修改时间
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestAdd(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/add", params, METHOD_POST, response, context);
    }



    /**
    * 用户:更新
    * @param  params  参数
    *                        id                  int                 *         id
    *                        status              integer                       0: 已删除  1: 正常 2: 草稿  3：待审
    *                        username            string                        用户名
    *                        telephone           string                        用户手机号
    *                        email               string                        邮箱
    *                        password            md5                           用户密码
    *                        genre               integer                       用户类型：1-爱心人士，2-机构
    *                        is_disabled         integer                       是否是残障人士：0-否，1-是
    *                        is_volunteer        integer                       是否是志愿者：0-否，1-是
    *                        avatar              string                        头像
    *                        name                string                        姓名
    *                        nickname            string                        昵称
    *                        occupation          string                        职业
    *                        age                 integer                       年龄
    *                        birthday            date                          生日、出生年月日
    *                        sex                 integer                       性别：0-未知，1-男，2-女
    *                        contact             string                        联系方式
    *                        profile             string                        个人介绍
    *                        area_id             integer                       地区id
    *                        town                string                        村镇
    *                        detail              string                        详细地址
    *                        last_longitude      float                         最后一次经度
    *                        last_latitude       float                         最后一次维度
    *                        level               integer                       用户级别：0-未激活用户 1-普通用户 5-普通管理员  9-超级管理员
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdate(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/update", params, METHOD_POST, response, context);
    }



    /**
    * 用户:列表
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        iscountall          bool                          是否统计总数 1是 0否
    *                        order               string                        排序方式
    *                        isreverse           int                           是否倒序 0否 1是
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       用户id
    *                        status              integer                       0: 已删除  1: 正常 2: 草稿  3：待审
    *                        username            string                        用户名
    *                        telephone           string                        用户手机号
    *                        email               string                        邮箱
    *                        password            md5                           用户密码
    *                        level               integer                       用户级别：0-未激活用户 1-普通用户 5-普通管理员  9-超级管理员
    *                        genre               integer                       用户类型：1-爱心人士，2-机构
    *                        is_disabled         integer                       是否是残障人士：0-否，1-是
    *                        is_volunteer        integer                       是否是志愿者：0-否，1-是
    *                        avatar              string                        头像
    *                        name                string                        姓名
    *                        nickname            string                        昵称
    *                        occupation          string                        职业
    *                        age                 integer                       年龄
    *                        birthdaystart       date                          >=起始时间（之后）：生日、出生年月日
    *                        birthdayend         date                          <结束时间（之前）：生日、出生年月日
    *                        sex                 integer                       性别：0-未知，1-男，2-女
    *                        contact             string                        联系方式
    *                        profile             string                        个人介绍
    *                        area_id             integer                       地区id
    *                        town                string                        村镇
    *                        detail              string                        详细地址
    *                        last_longitude      float                         最后一次经度
    *                        last_latitude       float                         最后一次维度
    *                        last_login_timestartdatetime                      >=起始时间（之后）：最后登录时间
    *                        last_login_timeend  datetime                      <结束时间（之前）：最后登录时间
    *                        last_password_timestartdatetime                      >=起始时间（之后）：最后一次密码修改时间
    *                        last_password_timeenddatetime                      <结束时间（之前）：最后一次密码修改时间
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
        return request("user/list", params, METHOD_GET, response, context);
    }



    /**
    * 用户:详情
    * @param  params  参数
    *                        id                  int                 *         id
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestDetail(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/detail", params, METHOD_GET, response, context);
    }



    /**
    * 用户:修改密码（不登录，需要验证短信）
    * @param  params  参数
    *                        telephone           string              *         用户手机号
    *                        verify_code         string              *         验证码
    *                        newpassword         md5                 *         密码
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdateWithVerifyCode(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/update_with_verify_code", params, METHOD_POST, response, context);
    }



    /**
    * 用户:修改密码／邮箱/手机（需要登录，并提供原始密码）
    * @param  params  参数
    *                        oldpassword         md5                           旧密码
    *                        newpassword         md5                           新密码
    *                        email               string                        邮箱
    *                        telephone           string                        用户手机号
    *                        verify_code         string                        验证码
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdateWithOldpassword(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/update_with_oldpassword", params, METHOD_POST, response, context);
    }



    /**
    * 用户:登录
    * @param  params  参数
    *                        account             string              *         支持手机号、用户名、邮箱登录
    *                        password            md5                 *         密码
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestLogin(Map<String, Object> params, final HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/login", params, METHOD_POST, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK()) {
                    Object authInfo = result.find("extraInfo>authInfo");
                    if (authInfo instanceof JsonObject) {
                        HaoConnect.setCurrentUserInfo(((JsonObject) authInfo).get("Userid").getAsString(), ((JsonObject) authInfo).get("Logintime").getAsString(), ((JsonObject) authInfo).get("Checkcode").getAsString());
                    }
                }
                response.onSuccess(result);
            }

            @Override
            public void onStart() {
                response.onStart();
            }

            @Override
            public void onFail(HaoResult result) {
                response.onFail(result);
            }
        }, context);
    }



    /**
    * 用户:联合登录
    * @param  params  参数
    *                        union_type          int                 *         登录方式：2QQ 3微博 4微信
    *                        union_token         string              *         联合登录唯一识别码
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUnionLogin(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/union_login", params, METHOD_POST, response, context);
    }



    /**
    * 用户:登录后绑定对应联合登录
    * @param  params  参数
    *                        union_type          int                 *         登录方式：2QQ 3微博 4微信
    *                        union_token         string              *         联合登录唯一识别码
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestSetUnionLogin(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/set_union_login", params, METHOD_POST, response, context);
    }



    /**
    * 用户:注销
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestLogOut(Map<String, Object> params, final HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/log_out", params, METHOD_GET, new HaoResultHttpResponseHandler() {
            @Override
            public void onSuccess(HaoResult result) {
                if (result.isResultsOK())
                {
                    HaoConnect.setCurrentUserInfo("","","");
                    response.onSuccess(result);
                }
            }

            @Override
            public void onStart() {
                response.onStart();
            }

            @Override
            public void onFail(HaoResult result) {
                response.onFail(result);
            }
        }, context);
    }



    /**
    * 用户:我的信息
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestGetMyDetail(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/get_my_detail", params, METHOD_GET, response, context);
    }



    /**
    * 用户:删除（仅供管理员测试期间用)
    * @param  params  参数
    *                        ids                 string                        多个id用逗号隔开
    *                        id                  integer                       
    *                        telephone           string                        用户手机号
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestDelete(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("user/delete", params, METHOD_POST, response, context);
    }

}