package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class CompanyImgResult extends HaoResult {
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

    /****/
    public Object findImgUrl()
    {
        return find("imgUrl");
    }

    
    public Object findTitle()
    {
        return find("title");
    }

    
    public Object findDescription()
    {
        return find("description");
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