package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class ResumeEducationExperienceResult extends HaoResult {
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

    /**学校名称**/
    public Object findShool()
    {
        return find("shool");
    }

    /**学习开始时间**/
    public Object findLearnMin()
    {
        return find("learnMin");
    }

    /**学习结束时间**/
    public Object findLearnMax()
    {
        return find("learnMax");
    }

    /**所学专业**/
    public Object findProfession()
    {
        return find("profession");
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