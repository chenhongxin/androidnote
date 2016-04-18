package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class UserVolunteerResult extends HaoResult {
    /**志愿者id**/
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

    /** 获取图像 */
    public Object findAvatar()
    {
        return find("avatar");
    }

    /**创建用户id**/
    public Object findCreatedByUserID()
    {
        return find("createdByUserID");
    }

    /**名称：姓名**/
    public Object findName()
    {
        return find("name");
    }

    /**服务理念**/
    public Object findServiceConcept()
    {
        return find("serviceConcept");
    }

    /**服务内容**/
    public Object findServiceContent()
    {
        return find("serviceContent");
    }

    /**服务标签**/
    public Object findServiceTag()
    {
        return find("serviceTag");
    }

    /**身份证照片**/
    public Object findIDCardPicture()
    {
        return find("iDCardPicture");
    }

    /**是否加v:0-否，1-是**/
    public Object findIsVerified()
    {
        return find("isVerified");
    }

    /**地区id**/
    public Object findAreaID()
    {
        return find("areaID");
    }

    /** 获取地区信息 */
    public Object findAreaLocal()
    {
        return find("areaLocal");
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