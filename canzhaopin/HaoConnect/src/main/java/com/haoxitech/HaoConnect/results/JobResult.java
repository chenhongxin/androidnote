package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class JobResult extends HaoResult {
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

    /**公司ID**/
    public Object findCompanyID()
    {
        return find("companyID");
    }

    
    public Object findUserLocal()
    {
        return find("userLocal");
    }

    
    public Object findCompanyLocal()
    {
        return find("companyLocal");
    }

    /**职位标题**/
    public Object findTitle()
    {
        return find("title");
    }

    /**工作经验 0.不限  1.一年以内  2.1-2年  3.2-5年   4.5年以上**/
    public Object findExperience()
    {
        return find("experience");
    }

    /**学历  0.不限 1.初中及以下  2.高中  3.大专  4.本科  5.研究生  6.博士  7.硕士**/
    public Object findEducational()
    {
        return find("educational");
    }

    
    public Object findIsTop()
    {
        return find("isTop");
    }

    /**1.全职   2.兼职   3.实习**/
    public Object findGenre()
    {
        return find("genre");
    }

    /**岗位标签**/
    public Object findTagids()
    {
        return find("tagids");
    }

    
    public Object findTagLabel()
    {
        return find("tagLabel");
    }

    /**0：面议  1：指定范围**/
    public Object findSalaryType()
    {
        return find("salaryType");
    }

    /**工资最小金额  单价（元）**/
    public Object findSalaryMin()
    {
        return find("salaryMin");
    }

    /**工资最大金额  单价（元）**/
    public Object findSalaryMax()
    {
        return find("salaryMax");
    }

    
    public Object findSalaryLabel()
    {
        return find("salaryLabel");
    }

    /**行业**/
    public Object findIndustryID()
    {
        return find("industryID");
    }

    
    public Object findIndustryLabel()
    {
        return find("industryLabel");
    }

    /**福利**/
    public Object findWelfares()
    {
        return find("welfares");
    }

    
    public Object findWelfaresLabel()
    {
        return find("welfaresLabel");
    }

    /**职位描述**/
    public Object findJobDesc()
    {
        return find("jobDesc");
    }

    /**职位要求**/
    public Object findJobClaim()
    {
        return find("jobClaim");
    }

    /**0: 已删除  1: 正常 2: 待审  3：草稿**/
    public Object findStatus()
    {
        return find("status");
    }

    
    public Object findFavorite()
    {
        return find("favorite");
    }

    
    public Object findCloseTime()
    {
        return find("closeTime");
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