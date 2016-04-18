package com.haoxitech.HaoConnect.connects;

import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;

import android.content.Context;

public class SmsVerifyConnect extends HaoConnect {


    /**
     * 验证码:查看表结构（限管理员）
     *
     * @param params   参数
     * @param response 异步方法
     * @param context  请求所在的页面对象
     */
    public static RequestHandle requestColumns(Map<String, Object> params, HaoResultHttpResponseHandler response, Context context) {
        return request("sms_verify/columns", params, METHOD_GET, response, context);
    }


    /**
     * 验证码:列表
     *
     * @param params   参数
     *                 page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
     *                 size                int                 *         分页大小
     *                 iscountall          bool                          是否统计总数 1是 0否
     *                 order               string                        排序方式
     *                 isreverse           int                           是否倒序 0否 1是
     *                 ids                 string                        多个id用逗号隔开
     *                 id                  integer
     *                 telephone           string
     *                 user_id             integer
     *                 verify_code         string                        验证码
     *                 use_for             integer                       验证码用途1：注册用 2：登陆用 3：找回密码用
     *                 create_timestart    datetime                      >=起始时间（之后）：创建时间
     *                 create_timeend      datetime                      <结束时间（之前）：创建时间
     *                 verify_timestart    datetime                      >=起始时间（之后）：验证时间
     *                 verify_timeend      datetime                      <结束时间（之前）：验证时间
     *                 keyword             string                        检索关键字
     * @param response 异步方法
     * @param context  请求所在的页面对象
     */
    public static RequestHandle requestList(Map<String, Object> params, HaoResultHttpResponseHandler response, Context context) {
        return request("sms_verify/list", params, METHOD_GET, response, context);
    }


    /**
     * 验证码:发送一条验证码到手机
     *
     * @param params   参数
     *                 telephone           string              *
     *                 usefor              int                 *         验证码用途
     * @param response 异步方法
     * @param context  请求所在的页面对象
     */
    public static RequestHandle requestSendVerifyCode(Map<String, Object> params, HaoResultHttpResponseHandler response, Context context) {
        return request("sms_verify/send_verify_code", params, METHOD_POST, response, context);
    }

    /**
     * 验证码:确认验证码是否正确
     *
     * @param params   参数
     *                 telephone           string              *         手机号码
     *                 verify_code         string              *         验证码
     * @param response 异步方法
     * @param context  请求所在的页面对象
     */
    public static RequestHandle requestCheckVerifyCode(Map<String, Object> params, HaoResultHttpResponseHandler response, Context context) {
        return request("sms_verify/check_verify_code", params, METHOD_POST, response, context);
    }

    /**
     * 验证码:剩余短信量
     *
     * @param params   参数
     * @param response 异步方法
     * @param context  请求所在的页面对象
     */
    public static RequestHandle requestGetBalance(Map<String, Object> params, HaoResultHttpResponseHandler response, Context context) {
        return request("sms_verify/get_balance", params, METHOD_GET, response, context);
    }

}