package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.WxUserInfo;

public interface WxUserInfoService {
    /**
     * 新增
     */
    public int insert(WxUserInfo wxUserInfo);


    /**
     * 更新
     */
    public int updateById(WxUserInfo wxUserInfo);


    /**
     * 根据唯一键 查询
     */
    public WxUserInfo selectByUnionKey(String platform, String appType, String openId);
}
