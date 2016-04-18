package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class LaborTypeResult extends HaoResult {
    /**项目类型**/
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

    /**名称**/
    public Object findTitle()
    {
        return find("title");
    }

    /**logo**/
    public Object findLogo()
    {
        return find("logo");
    }

    /**
     * 获取介绍
     * @return string
     */
    public Object findIntroduction()
    {
        return find("introduction");
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