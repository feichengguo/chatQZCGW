package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.WxUserInfoMapper;
import com.tencent.wxcloudrun.model.WxUserInfo;
import com.tencent.wxcloudrun.service.WxUserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WxUserInfoServiceImpl implements WxUserInfoService {
    @Resource
    private WxUserInfoMapper wxUserInfoMapper;


    @Override
    public int insert(WxUserInfo wxUserInfo) {
        return wxUserInfoMapper.insert(wxUserInfo);
    }


    @Override
    public int updateById(WxUserInfo wxUserInfo) {
        if (wxUserInfo == null || wxUserInfo.getId() == null) {
            return 0;
        }
        return wxUserInfoMapper.updateById(wxUserInfo);
    }


    @Override
    public WxUserInfo selectByUnionKey(String platform, String appType, String openId) {
        return wxUserInfoMapper.selectByUnionKey(platform, appType, openId);
    }


}
