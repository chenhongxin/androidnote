package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class DeliveryLogResult extends HaoResult {
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

    /**查看人ID**/
    public Object findLookUserID()
    {
        return find("lookUserID");
    }

    /**简历ID**/
    public Object findResumeID()
    {
        return find("resumeID");
    }

    /**1.查看  2.邀请面试  3.拒绝**/
    public Object findEditStatus()
    {
        return find("editStatus");
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