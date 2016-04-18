package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class TagResult extends HaoResult {
    /****/
    public Object findId()
    {
        return find("id");
    }

    /****/
    public Object findUserID()
    {
        return find("userID");
    }

    /**标签名称**/
    public Object findTagName()
    {
        return find("tagName");
    }

    /**0: 已删除  1: 正常**/
    public Object findStatus()
    {
        return find("status");
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