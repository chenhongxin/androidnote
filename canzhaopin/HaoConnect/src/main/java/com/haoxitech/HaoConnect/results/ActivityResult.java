package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class ActivityResult extends HaoResult {
    /**活动id**/
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

    /**活动类型id**/
    public Object findTypeID()
    {
        return find("typeID");
    }

    /**
     * 获取类型名称
     * @return mixed
     */
    public Object findTypeTitle()
    {
        return find("typeTitle");
    }

    /**发布活动的组织结构（id）**/
    public Object findServOrgID()
    {
        return find("servOrgID");
    }

    /**活动标题**/
    public Object findTitle()
    {
        return find("title");
    }

    /**活动图片**/
    public Object findPicture()
    {
        return find("picture");
    }

    /**活动内容**/
    public Object findContent()
    {
        return find("content");
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

    /**开始时间**/
    public Object findStartTime()
    {
        return find("startTime");
    }

    /**结束时间**/
    public Object findEndTime()
    {
        return find("endTime");
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