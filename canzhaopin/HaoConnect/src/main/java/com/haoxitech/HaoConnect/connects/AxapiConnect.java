package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class AxapiConnect extends HaoConnect {


    /**
    * 接口工具:Say Hello
    * @param  params  参数
    *                        name                string                        name
    *                        password            md5                           password
    *                        avatar              file                          avatar
    *                        photos[]            file                          avatar
    *                        age                 int                           age
    *                        content             string                        content
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestSayHello(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("axapi/SayHello", params, METHOD_GET, response, context);
    }



    /**
    * 接口工具:测试首页数据
    * @param  params  参数
    *                        sleep               int                           延迟时间（单位：秒）
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestGetHomeTableForTest(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("axapi/get_home_table_for_test", params, METHOD_GET, response, context);
    }



    /**
    * 接口工具:查看日志（限管理员）
    * @param  params  参数
    *                        page                int                 *         分页，第一页为1，第二页为2，最后一页为-1
    *                        size                int                 *         分页大小
    *                        type                string              *         日志类型
    *                        datetime            datetime                      指定日志所在日期（默认当日）
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestLoadLogList(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("axapi/LoadLogList", params, METHOD_GET, response, context);
    }



    /**
    * 接口工具:MHC代码文件快速生成（限管理员）
    * @param  params  参数
    *                        -t                  string              *         表名
    *                        -name               string                        接口分类（中文）如：用户、设备、留言
    *                        -pri                string                        默认取PRI且auto_increment的字段。若取不到，则可以在此处填一个字段，否则就是空了哦
    *                        -rm                 string                        是否删除代码文件
    *                        -update             string                        是否更新代码文件
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestCreateMhcWithTableName(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("axapi/create_mhc_with_table_name", params, METHOD_POST, response, context);
    }



    /**
    * 接口工具:HaoConnect代码文件快速生成（限管理员）
    * @param  params  参数
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUpdateCodesOfHaoConnect(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("axapi/update_codes_of_hao_connect", params, METHOD_POST, response, context);
    }



    /**
    * 接口工具:获得Model对应字段的描述
    * @param  params  参数
    *                        model_name          string              *         model名
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestGetDescriptionsInModel(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("axapi/get_descriptions_in_model", params, METHOD_GET, response, context);
    }

}