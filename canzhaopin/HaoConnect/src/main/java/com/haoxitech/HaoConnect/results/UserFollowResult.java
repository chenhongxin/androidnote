package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class UserFollowResult extends HaoResult {
    /**点赞id**/
    public Object findId()
    {
        return find("id");
    }

    /**状态：0-已删除，1-正常，2-草稿，3-待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**关注的用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**被关注的目标id**/
    public Object findTargetUserID()
    {
        return find("targetUserID");
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