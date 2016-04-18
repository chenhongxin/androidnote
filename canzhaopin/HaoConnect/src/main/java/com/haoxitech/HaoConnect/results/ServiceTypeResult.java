package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class ServiceTypeResult extends HaoResult {
    /**类型id**/
    public Object findId()
    {
        return find("id");
    }

    /**0: 已删除  1: 正常 2: 草稿  3：待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**创建用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**服务大类id**/
    public Object findServiceGenreID()
    {
        return find("serviceGenreID");
    }

    /**类型标签名**/
    public Object findTag()
    {
        return find("tag");
    }

    /**logo**/
    public Object findLogo()
    {
        return find("logo");
    }

    /**创建时间**/
    public Object findCreateTime()
    {
        return find("createTime");
    }

    /**修改时间**/
    public Object findModifyTime()
    {
        return find("modifyTime");
    }

}