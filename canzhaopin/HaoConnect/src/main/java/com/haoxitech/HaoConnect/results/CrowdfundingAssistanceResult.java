package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class CrowdfundingAssistanceResult extends HaoResult {
    /**援助id**/
    public Object findId()
    {
        return find("id");
    }

    /**状态：0-已删除，1-正常，2-草稿，3-待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**援助用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**众筹id**/
    public Object findCrowdfundingID()
    {
        return find("crowdfundingID");
    }

    /**援助状态：1-成功，2-失败**/
    public Object findAssistStatus()
    {
        return find("assistStatus");
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