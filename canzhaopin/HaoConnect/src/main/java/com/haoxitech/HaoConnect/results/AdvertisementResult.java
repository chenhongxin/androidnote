package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class AdvertisementResult extends HaoResult {
    /**广告id**/
    public Object findId()
    {
        return find("id");
    }

    /**0: 已删除  1: 正常 2: 草稿  3：待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**创建者用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**适用设备：1-手机，2-平板，3-pc,4-通用**/
    public Object findDeviceType()
    {
        return find("deviceType");
    }

    /**广告类型：0-未知，1-首页banner广浩，2-海报， 3-一般广告**/
    public Object findGenre()
    {
        return find("genre");
    }

    /**广告图片**/
    public Object findPicture()
    {
        return find("picture");
    }

    
    public Object findAdType()
    {
        return find("adType");
    }

    /**操作值，type为1这里为url**/
    public Object findAdValue()
    {
        return find("adValue");
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