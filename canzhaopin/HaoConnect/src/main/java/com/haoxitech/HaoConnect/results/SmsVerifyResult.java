package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class SmsVerifyResult extends HaoResult {
    /****/
    public Object findId()
    {
        return find("id");
    }

    /****/
    public Object findTelephone()
    {
        return find("telephone");
    }

    /****/
    public Object findUserID()
    {
        return find("userID");
    }

    /**验证码**/
    public Object findVerifyCode()
    {
        return find("verifyCode");
    }

    /**验证码用途1：注册用 2：登陆用 3：找回密码用**/
    public Object findUseFor()
    {
        return find("useFor");
    }

    /**创建时间**/
    public Object findCreateTime()
    {
        return find("createTime");
    }

    /**验证时间**/
    public Object findVerifyTime()
    {
        return find("verifyTime");
    }

}