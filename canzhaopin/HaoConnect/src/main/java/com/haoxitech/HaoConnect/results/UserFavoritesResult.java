package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class UserFavoritesResult extends HaoResult {
    /**收藏id**/
    public Object findId()
    {
        return find("id");
    }

    /**状态：0-已删除，1-正常，2-草稿，3-待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**收藏用户的id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**类型：0-未知，1-新闻，2-活动，3.劳动项目，4-服务机构，5-理想众筹，6-政策**/
    public Object findFavoritesType()
    {
        return find("favoritesType");
    }

    /**收藏的目标id**/
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