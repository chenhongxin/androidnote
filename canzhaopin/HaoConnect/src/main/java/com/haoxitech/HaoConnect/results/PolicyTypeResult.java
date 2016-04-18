package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class PolicyTypeResult extends HaoResult {
    /**政策类型id**/
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

    /**类型分类：1-标签，2-分类**/
    public Object findGenre()
    {
        return find("genre");
    }

    /**政策标签**/
    public Object findTag()
    {
        return find("tag");
    }

    /**类型logo**/
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