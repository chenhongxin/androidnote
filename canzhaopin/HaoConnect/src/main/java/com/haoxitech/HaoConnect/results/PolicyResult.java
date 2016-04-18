package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class PolicyResult extends HaoResult {
    /**政策id**/
    public Object findId()
    {
        return find("id");
    }

    /**状态：0-已删除，1-正常，2-草稿，3-待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**创建用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**政策类型id**/
    public Object findTypeID()
    {
        return find("typeID");
    }

    /**政策名称**/
    public Object findTitle()
    {
        return find("title");
    }

    /**政策内容**/
    public Object findContent()
    {
        return find("content");
    }

    /**浏览人数**/
    public Object findViewCount()
    {
        return find("viewCount");
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