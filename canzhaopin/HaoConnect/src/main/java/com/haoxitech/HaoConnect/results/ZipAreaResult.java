package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class ZipAreaResult extends HaoResult {
    /****/
    public Object findId()
    {
        return find("id");
    }

    /****/
    public Object findAreaName()
    {
        return find("areaName");
    }

    /**父级区域**/
    public Object findAreaParent()
    {
        return find("areaParent");
    }

    /**一级区域**/
    public Object findAreaMain()
    {
        return find("areaMain");
    }

    /**二级区域**/
    public Object findAreaSecond()
    {
        return find("areaSecond");
    }

    /**三级区域**/
    public Object findAreaThird()
    {
        return find("areaThird");
    }

}