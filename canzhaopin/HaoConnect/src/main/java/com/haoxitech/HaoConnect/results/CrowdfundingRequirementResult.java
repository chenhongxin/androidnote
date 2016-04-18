package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class CrowdfundingRequirementResult extends HaoResult {
    /**需求id**/
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

    /****/
    public Object findTitle()
    {
        return find("title");
    }

    
    public Object findRequirementType()
    {
        return find("requirementType");
    }

    /**需求值；type为1则为金额值**/
    public Object findRequireValue()
    {
        return find("requireValue");
    }

    /**需求内容**/
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