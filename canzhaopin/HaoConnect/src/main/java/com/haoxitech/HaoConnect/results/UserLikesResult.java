package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class UserLikesResult extends HaoResult {
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

    /**点赞的用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**类型：0-未知，1-新闻，2-活动，3.劳动项目，4-服务机构，5-理想众筹，6-政策**/
    public Object findLikesType()
    {
        return find("likesType");
    }

    /**点赞的目标id**/
    public Object findTargetID()
    {
        return find("targetID");
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