package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class PollResult extends HaoResult {
    /**投票评选id**/
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

    /**投票类型：1-单选，2-多选**/
    public Object findPollType()
    {
        return find("pollType");
    }

    /**最多选择多少项**/
    public Object findMaxNum()
    {
        return find("maxNum");
    }

    /**投票编号**/
    public Object findPollNo()
    {
        return find("pollNo");
    }

    /**投票标题**/
    public Object findTitle()
    {
        return find("title");
    }

    /**投票图片**/
    public Object findPicture()
    {
        return find("picture");
    }

    /**投票人数**/
    public Object findPolledCount()
    {
        return find("polledCount");
    }

    /**浏览次数**/
    public Object findViewCount()
    {
        return find("viewCount");
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