package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class MessageLogResult extends HaoResult {
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

    /**消息类型  1.系统消息  2.简历浏览消息  3.面试邀请消息**/
    public Object findMType()
    {
        return find("mType");
    }

    /**消息ID**/
    public Object findMValue()
    {
        return find("mValue");
    }

    /**公司ID**/
    public Object findCompanyID()
    {
        return find("companyID");
    }

    /**消息内容**/
    public Object findContent()
    {
        return find("content");
    }

    /**是否查看 1是  0否**/
    public Object findIsLook()
    {
        return find("isLook");
    }

    /**0 已删除  1正常  2草稿  3.待审**/
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