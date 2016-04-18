package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class SystemNotificationResult extends HaoResult {
    /**通知id**/
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
    public Object findUserID()
    {
        return find("userID");
    }

    /**通知类型：1-单独通知，2-通知所有**/
    public Object findNotificationType()
    {
        return find("notificationType");
    }

    /**接受通知的用户id**/
    public Object findTargetUserID()
    {
        return find("targetUserID");
    }

    /**标题**/
    public Object findTitle()
    {
        return find("title");
    }

    /**通知内容**/
    public Object findContent()
    {
        return find("content");
    }

    /**是否未读：0-已读，1-未读**/
    public Object findUnread()
    {
        return find("unread");
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