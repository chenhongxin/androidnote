package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class UserDisabledPersonResult extends HaoResult {
    /**残障人士id**/
    public Object findId()
    {
        return find("id");
    }

    /**状态：0-删除，1-正常，2-草稿，3-待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**状态：1-待审核，2-审核通过，3-审核未通过**/
    public Object findAuthenticatedState()
    {
        return find("authenticatedState");
    }

    /**用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**创建用户id**/
    public Object findCreatedByUserID()
    {
        return find("createdByUserID");
    }

    /**残疾人编号**/
    public Object findNumber()
    {
        return find("number");
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

    /**详细地址**/
    public Object findDetail()
    {
        return find("detail");
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