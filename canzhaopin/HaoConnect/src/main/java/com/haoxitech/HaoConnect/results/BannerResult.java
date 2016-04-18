package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class BannerResult extends HaoResult {
    /****/
    public Object findId()
    {
        return find("id");
    }

    /**用户ID**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**轮播图名称**/
    public Object findTitle()
    {
        return find("title");
    }

    /**照片预览（单个七牛网址）**/
    public Object findPhoto()
    {
        return find("photo");
    }

    /**轮播图类型 1招聘详情 20webview 21外跳链接**/
    public Object findAdType()
    {
        return find("adType");
    }

    /**轮播图有效值 根据adType不同，此处用途不同，如adType为20此处则为url，如adType为1此处则为套系ID**/
    public Object findAdValue()
    {
        return find("adValue");
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

    /**0: 不存在  1: 正常 2: 草稿  3：待审**/
    public Object findStatus()
    {
        return find("status");
    }

}