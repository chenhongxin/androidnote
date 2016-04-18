package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class ServiceOrganizationResult extends HaoResult {
    /**组织id**/
    public Object findId()
    {
        return find("id");
    }

    /**0: 已删除  1: 正常 2: 草稿  3：待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**状态：1-待审核，2-审核通过，3-审核未通过**/
    public Object findAuthenticatedState()
    {
        return find("authenticatedState");
    }

    /**创建用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**组织名称**/
    public Object findTitle()
    {
        return find("title");
    }

    /**服务机构类型id**/
    public Object findServiceTypeID()
    {
        return find("serviceTypeID");
    }

    /**机构执照**/
    public Object findOrganizationLicense()
    {
        return find("organizationLicense");
    }

    /**机构图片**/
    public Object findPicture()
    {
        return find("picture");
    }

    /**内容介绍**/
    public Object findContent()
    {
        return find("content");
    }

    /**服务标签**/
    public Object findServiceTag()
    {
        return find("serviceTag");
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

    /**联系方式**/
    public Object findContact()
    {
        return find("contact");
    }

    /**浏览人数**/
    public Object findViewCount()
    {
        return find("viewCount");
    }

    /**机构所在经度**/
    public Object findLongitude()
    {
        return find("longitude");
    }

    /**机构所在维度**/
    public Object findLatitude()
    {
        return find("latitude");
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