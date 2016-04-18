package com.haoxitech.HaoConnect.results;

import com.haoxitech.HaoConnect.HaoResult;

public class CrowdfundingResult extends HaoResult {
    /**众筹id**/
    public Object findId()
    {
        return find("id");
    }

    /**状态：0-已删除，1-正常，2-草稿，3-待审**/
    public Object findStatus()
    {
        return find("status");
    }

    /**创建者用户id**/
    public Object findUserID()
    {
        return find("userID");
    }

    /**众筹类型id**/
    public Object findCrowdfundingTypeID()
    {
        return find("crowdfundingTypeID");
    }

    /**众筹标题**/
    public Object findTitle()
    {
        return find("title");
    }

    /**
     * 获取图片
     * @return string
     */
    public Object findPicture()
    {
        return find("picture");
    }

    /**
     * 获取声音URL string
     * @return string
     */
    public Object findVoice()
    {
        return find("voice");
    }

    /**
     * 获取语音时长
     * @return int
     */
    public Object findVoiceLength()
    {
        return find("voiceLength");
    }

    /**众筹项目内容**/
    public Object findContent()
    {
        return find("content");
    }

    /**项目进程：0-未开始，1-进行中，2-已成功，3-已失败**/
    public Object findProcess()
    {
        return find("process");
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

    /**援助人数**/
    public Object findAssistanceCount()
    {
        return find("assistanceCount");
    }

    /**浏览人数**/
    public Object findViewCount()
    {
        return find("viewCount");
    }

    /**赞的人数**/
    public Object findLikesCount()
    {
        return find("likesCount");
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