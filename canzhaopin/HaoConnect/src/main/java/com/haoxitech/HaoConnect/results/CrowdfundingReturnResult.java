package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class CrowdfundingReturnResult extends HaoResult {
    /**众筹回馈id**/
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

    /**众筹id**/
    public Object findCrowdfundingID()
    {
        return find("crowdfundingID");
    }

    /**回馈详情**/
    public Object findContent()
    {
        return find("content");
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