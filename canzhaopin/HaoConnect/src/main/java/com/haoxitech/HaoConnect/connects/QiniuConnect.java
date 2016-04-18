package com.haoxitech.HaoConnect.connects;
import com.haoxitech.HaoConnect.HaoConnect;
import com.haoxitech.HaoConnect.HaoResultHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import java.util.Map;
import android.content.Context;

public class QiniuConnect extends HaoConnect {


    /**
    * 七牛:取得一个授权凭证用于直传文件给七牛 (推荐：步骤1）
    * @param  params  参数
    *                        md5                 string              *         文件的MD5值
    *                        filesize            int                 *         文件大小
    *                        filetype            string              *         文件后缀如jpg,png等
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestGetUploadTokenForQiniu(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("qiniu/getUploadTokenForQiniu", params, METHOD_POST, response, context);
    }



    /**
    * 七牛:直传文件到七牛服务器 (推荐：步骤2）
    * @param  params  参数
    *                        token               string              *         来自服务器端的uploadToken数据
    *                        file                file                *         本地文件（单个）
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUploadQiniuCom(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("http://upload.qiniu.com", params, METHOD_POST, response, context);
    }



    /**
    * 七牛:上传本地单个文件经服务器中转到七牛
    * @param  params  参数
    *                        file                file                *         本地文件（单个）
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUploadSingleFile(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("qiniu/uploadSingleFile", params, METHOD_POST, response, context);
    }



    /**
    * 七牛:上传本地多个文件经服务器中转到七牛
    * @param  params  参数
    *                        files[]             file                *         本地文件（单个）
    * @param  response 异步方法
    * @param  context  请求所在的页面对象
    */
    public static RequestHandle requestUploadMultipleFiles(Map<String, Object> params,  HaoResultHttpResponseHandler response, Context context)
    {
        return request("qiniu/uploadMultipleFiles", params, METHOD_POST, response, context);
    }

}