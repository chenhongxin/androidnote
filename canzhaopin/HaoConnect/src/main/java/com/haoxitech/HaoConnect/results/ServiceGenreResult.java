package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class ServiceGenreResult extends HaoResult {
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

    /**展示标志：1-都展示，2-只APP端展示，3-只web页面展示，4-都不展示**/
    public Object findShowFlag()
    {
        return find("showFlag");
    }

    
    public Object findActionType()
    {
        return find("actionType");
    }

    /**操作值：0-未知**/
    public Object findActionValue()
    {
        return find("actionValue");
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