package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class PollResultResult extends HaoResult {
    /**投票结果**/
    public Object findId()
    {
        return find("id");
    }

    /**状态：0-删除，1-正常，2-草稿，3-待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**创建用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**投票id**/
    public Object findPollID()
    {
        return find("pollID");
    }

    /**投票的选项结果，多选项时用逗号隔开**/
    public Object findPoll()
    {
        return find("poll");
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