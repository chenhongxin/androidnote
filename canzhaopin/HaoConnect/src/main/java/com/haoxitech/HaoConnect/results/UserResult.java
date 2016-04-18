package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class UserResult extends HaoResult {
    /**用户id**/
    public Object findId()
    {
        return find("id");
    }

    /**0: 已删除  1: 正常 2: 草稿  3：待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**用户名**/
    public Object findUsername()
    {
        return find("username");
    }

    /**用户手机号**/
    public Object findTelephone()
    {
        return find("telephone");
    }

    /**用户手机号**/
    public Object findTelephoneLocal()
    {
        return find("telephoneLocal");
    }

    /**邮箱**/
    public Object findEmail()
    {
        return find("email");
    }

    /**用户密码**/
    public Object findPassword()
    {
        return find("password");
    }

    /**用户级别：0-未激活用户 1-普通用户 5-普通管理员  9-超级管理员**/
    public Object findLevel()
    {
        return find("level");
    }

    /**用户类型：1-爱心人士，2-机构**/
    public Object findGenre()
    {
        return find("genre");
    }

    /**是否是残障人士：0-否，1-是**/
    public Object findIsDisabled()
    {
        return find("isDisabled");
    }

    /**是否是志愿者：0-否，1-是**/
    public Object findIsVolunteer()
    {
        return find("isVolunteer");
    }

    /**头像**/
    public Object findAvatar()
    {
        return find("avatar");
    }

    /**姓名**/
    public Object findName()
    {
        return find("name");
    }

    /**昵称**/
    public Object findNickname()
    {
        return find("nickname");
    }

    /**职业**/
    public Object findOccupation()
    {
        return find("occupation");
    }

    /**年龄**/
    public Object findAge()
    {
        return find("age");
    }

    /**生日、出生年月日**/
    public Object findBirthday()
    {
        return find("birthday");
    }

    /**性别：0-未知，1-男，2-女**/
    public Object findSex()
    {
        return find("sex");
    }

    /**联系方式**/
    public Object findContact()
    {
        return find("contact");
    }

    /**个人介绍**/
    public Object findProfile()
    {
        return find("profile");
    }

    /**地区id**/
    public Object findAreaID()
    {
        return find("areaID");
    }

    /**村镇**/
    public Object findTown()
    {
        return find("town");
    }

    
    public Object findCompanyID()
    {
        return find("companyID");
    }

    /**详细地址**/
    public Object findDetail()
    {
        return find("detail");
    }

    /**最后一次经度**/
    public Object findLastLongitude()
    {
        return find("lastLongitude");
    }

    /**最后一次维度**/
    public Object findLastLatitude()
    {
        return find("lastLatitude");
    }

    /**最后登录时间**/
    public Object findLastLoginTime()
    {
        return find("lastLoginTime");
    }

    /**最后一次密码修改时间**/
    public Object findLastPasswordTime()
    {
        return find("lastPasswordTime");
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