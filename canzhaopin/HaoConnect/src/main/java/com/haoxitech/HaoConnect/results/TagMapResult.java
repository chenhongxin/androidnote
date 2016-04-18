package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class TagMapResult extends HaoResult {
    /**标签映射id**/
    public Object findId()
    {
        return find("id");
    }

    /**0: 已删除  1: 正常 2: 草稿  3：待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**创建用户id**/
    public Object findUserid()
    {
        return find("userid");
    }

    /**标签id**/
    public Object findTagID()
    {
        return find("tagID");
    }

    /**0-未知，1-志愿者标签，2-劳动项目，3-群标签**/
    public Object findMapType()
    {
        return find("mapType");
    }

    /**具有该标签的对象id，由mapType指定类型**/
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