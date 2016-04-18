package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class DeviceResult extends HaoResult {
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

    /**设备token 友盟或iOS自带**/
    public Object findDeviceToken()
    {
        return find("deviceToken");
    }

    /**1：浏览器设备 2：pc设备 3：Android设备 4：ios设备 5：windows phone设备**/
    public Object findDeviceType()
    {
        return find("deviceType");
    }

    /**设备名称 如iPhone6Plus**/
    public Object findDeviceInfo()
    {
        return find("deviceInfo");
    }

    /**应用识别信息**/
    public Object findClientInfo()
    {
        return find("clientInfo");
    }

    /**设备应用版本号**/
    public Object findClientVersion()
    {
        return find("clientVersion");
    }

    /**是否允许推送 1允许 2不允许**/
    public Object findIsPushAllowed()
    {
        return find("isPushAllowed");
    }

    /**推送禁止设定（逗号分隔、默认全推）**/
    public Object findDenyPushTypes()
    {
        return find("denyPushTypes");
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

    /**最后一次登录时间**/
    public Object findLastLoginTime()
    {
        return find("lastLoginTime");
    }

    /**推送模式（iOS）,1是开发模式  2是正式环境**/
    public Object findDeployStatus()
    {
        return find("deployStatus");
    }

    /**声音：1开 0关**/
    public Object findIsSound()
    {
        return find("isSound");
    }

    /**震动：1开 0关**/
    public Object findIsShake()
    {
        return find("isShake");
    }

}