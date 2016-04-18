package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class ResumeExperienceResult extends HaoResult {
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

    /**简历ID**/
    public Object findResumeID()
    {
        return find("resumeID");
    }

    /**工作开始时间**/
    public Object findExperienceMin()
    {
        return find("experienceMin");
    }

    /**工作结束时间**/
    public Object findExperienceMax()
    {
        return find("experienceMax");
    }

    /**职位**/
    public Object findJobs()
    {
        return find("jobs");
    }

    /**公司名称**/
    public Object findCompany()
    {
        return find("company");
    }

    /**月薪**/
    public Object findSalary()
    {
        return find("salary");
    }

    /**公司联系电话**/
    public Object findTel()
    {
        return find("tel");
    }

    /**0: 已删除  1: 正常**/
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