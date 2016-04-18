package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class ActivityRegistrationResult extends HaoResult {
    /**活动报名id**/
    public Object findId()
    {
        return find("id");
    }

    /**状态：0-已删除，1-正常，2-草稿，3-待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**报名用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**活动id**/
    public Object findActivityID()
    {
        return find("activityID");
    }

    
    public Object findActivityLocal()
    {
        return find("activityLocal");
    }

    /**报名者姓名**/
    public Object findName()
    {
        return find("name");
    }

    /**报名者电话**/
    public Object findTelephone()
    {
        return find("telephone");
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