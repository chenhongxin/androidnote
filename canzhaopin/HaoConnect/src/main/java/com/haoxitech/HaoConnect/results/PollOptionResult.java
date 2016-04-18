package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class PollOptionResult extends HaoResult {
    /**投票选项id**/
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

    /**选项序号**/
    public Object findSerialNo()
    {
        return find("serialNo");
    }

    /**选项图片**/
    public Object findPicture()
    {
        return find("picture");
    }

    /**选项内容**/
    public Object findContent()
    {
        return find("content");
    }

    /**该选项投票数量**/
    public Object findPolledCount()
    {
        return find("polledCount");
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