package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class DeliveryResult extends HaoResult {
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

    /**简历ID**/
    public Object findResumeID()
    {
        return find("resumeID");
    }

    /**公司ID**/
    public Object findCompanyID()
    {
        return find("companyID");
    }

    /**0.未读  1.已读  2.邀请面试  3.拒绝**/
    public Object findEditStatus()
    {
        return find("editStatus");
    }

    
    public Object findResumeLocal()
    {
        return find("resumeLocal");
    }

    
    public Object findCompanyLocal()
    {
        return find("companyLocal");
    }

    
    public Object findUserLocal()
    {
        return find("userLocal");
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