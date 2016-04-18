package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class LaborDockResult extends HaoResult {
    /**劳动项目对接id**/
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

    /**对接类型：0-未知，1-残障人士、2-阳光之家,3-阳光基地**/
    public Object findDockedType()
    {
        return find("dockedType");
    }

    /**状态：0-取消，1-进行中，2-对接失败，3-对接成功，4-暂停，5-成功完成**/
    public Object findDockStatus()
    {
        return find("dockStatus");
    }

    /**劳动项目id**/
    public Object findLaborProjectID()
    {
        return find("laborProjectID");
    }

    /**
     * 获取对接的劳动项目详情.0
     */
    public Object findLaborLocal()
    {
        return find("laborLocal");
    }

    /**组织机构id**/
    public Object findServOrgID()
    {
        return find("servOrgID");
    }

    /**
     * 获取机构相关信息
     * @return ServiceOrganizationModel
     */
    public Object findServOrgLocal()
    {
        return find("servOrgLocal");
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