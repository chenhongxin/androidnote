package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class NewsResult extends HaoResult {
    /**新闻id**/
    public Object findId()
    {
        return find("id");
    }

    /**状态：0-已删除，1-正常，2-草稿，3-待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**创建新闻用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**新闻类型id**/
    public Object findNewsTypeID()
    {
        return find("newsTypeID");
    }

    /**
     * 获取新闻类型标签
     * @return string
     */
    public Object findNewsTypeTitle()
    {
        return find("newsTypeTitle");
    }

    /**新闻标题**/
    public Object findTitle()
    {
        return find("title");
    }

    /**新闻摘要**/
    public Object findSummary()
    {
        return find("summary");
    }

    /**新闻图片**/
    public Object findPicture()
    {
        return find("picture");
    }

    /**新闻内容**/
    public Object findContent()
    {
        return find("content");
    }

    /**发布机构**/
    public Object findServiceOrgID()
    {
        return find("serviceOrgID");
    }

    /** 获取机构相关的信息 */
    public Object findServiceOrgTitle()
    {
        return find("serviceOrgTitle");
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
     * 获取地区信息
     * @return ZipAreaModel
     */
    public Object findAreaLocal()
    {
        return find("areaLocal");
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