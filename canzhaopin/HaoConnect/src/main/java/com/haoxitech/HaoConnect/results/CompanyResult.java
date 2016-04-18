package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class CompanyResult extends HaoResult {
    /****/
    public Object findId()
    {
        return find("id");
    }

    /**创建人**/
    public Object findUserID()
    {
        return find("userID");
    }

    
    public Object findFeature()
    {
        return find("feature");
    }

    /**公司名称**/
    public Object findTitle()
    {
        return find("title");
    }

    /**公司地址**/
    public Object findAddress()
    {
        return find("address");
    }

    /**地址ID**/
    public Object findZipAreaID()
    {
        return find("zipAreaID");
    }

    
    public Object findZipAreaLabel()
    {
        return find("zipAreaLabel");
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

    
    public Object findHrCount()
    {
        return find("hrCount");
    }

    
    public Object findJobCount()
    {
        return find("jobCount");
    }

    
    public Object findResumeCount()
    {
        return find("resumeCount");
    }

    /**1.国有企业  2.集体所有制  3.联营企业  4.三资企业   5.私营企业**/
    public Object findNature()
    {
        return find("nature");
    }

    /**公司规模  1.10人以下   2.10-30人   3.30-100人   4.100人以上**/
    public Object findScale()
    {
        return find("scale");
    }

    
    public Object findCompnyImg()
    {
        return find("compnyImg");
    }

    /**企业介绍**/
    public Object findCompanyDesc()
    {
        return find("companyDesc");
    }

    /**营业执照**/
    public Object findPermit()
    {
        return find("permit");
    }

    
    public Object findLng()
    {
        return find("lng");
    }

    
    public Object findLat()
    {
        return find("lat");
    }

    
    public Object findLogo()
    {
        return find("logo");
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