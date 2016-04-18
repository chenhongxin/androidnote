package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class SystemMessageResult extends HaoResult {
    /****/
    public Object findId()
    {
        return find("id");
    }

    /****/
    public Object findUserID()
    {
        return find("userID");
    }

    /**消息接收人**/
    public Object findReceive()
    {
        return find("receive");
    }

    /****/
    public Object findTitle()
    {
        return find("title");
    }

    /**消息内容**/
    public Object findContent()
    {
        return find("content");
    }

    /**0: 已删除  1: 正常 2: 待审  3：拒绝**/
    public Object findStatus()
    {
        return find("status");
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