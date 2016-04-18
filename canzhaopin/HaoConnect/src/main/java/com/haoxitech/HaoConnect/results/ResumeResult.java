package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class ResumeResult extends HaoResult {
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

    /**姓名**/
    public Object findUsername()
    {
        return find("username");
    }

    /**性别**/
    public Object findSex()
    {
        return find("sex");
    }

    /**期待职位**/
    public Object findExpectJob()
    {
        return find("expectJob");
    }

    /**期望待遇最小金额  单价（元）**/
    public Object findExpectSalaryMin()
    {
        return find("expectSalaryMin");
    }

    /**期望待遇最大金额  单价（元）**/
    public Object findExpectSalaryMax()
    {
        return find("expectSalaryMax");
    }

    /**工作经验**/
    public Object findExperience()
    {
        return find("experience");
    }

    /**联系电话**/
    public Object findTelephone()
    {
        return find("telephone");
    }

    /**残疾证编号**/
    public Object findCardNumber()
    {
        return find("cardNumber");
    }

    /**自我描述**/
    public Object findUserDesc()
    {
        return find("userDesc");
    }

    
    public Object findResumeEducationExperience()
    {
        return find("resumeEducationExperience");
    }

    
    public Object findResumeExperience()
    {
        return find("resumeExperience");
    }

    /**0: 已删除  1: 正常 2: 待审  3：草稿**/
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