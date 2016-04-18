package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class LaborProjectResult extends HaoResult {
    /**劳动项目id**/
    public Object findId()
    {
        return find("id");
    }

    /**状态：0-已删除，1-正常，2-草稿，3-待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**创建用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**劳动项目类型id**/
    public Object findLaborTypeID()
    {
        return find("laborTypeID");
    }

    /**项目名称**/
    public Object findTitle()
    {
        return find("title");
    }

    /**项目介绍**/
    public Object findIntroduction()
    {
        return find("introduction");
    }

    /**发布单位id**/
    public Object findServOrgID()
    {
        return find("servOrgID");
    }

    /**
     * 获取服务机构信息
     * @return ServiceOrganizationModel
     */
    public Object findServOrgLocal()
    {
        return find("servOrgLocal");
    }

    /**
     * 获取地区id
     * @return int
     */
    public Object findAreaID()
    {
        return find("areaID");
    }

    /**
     * 获取地区详情
     * @return ZipAreaModel
     */
    public Object findAreaLocal()
    {
        return find("areaLocal");
    }

    /**对接的数目**/
    public Object findDockedNumber()
    {
        return find("dockedNumber");
    }

    /**浏览人数**/
    public Object findViewCount()
    {
        return find("viewCount");
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