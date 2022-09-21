package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.AppInfoMapper;
import com.tencent.wxcloudrun.model.AppInfo;
import com.tencent.wxcloudrun.service.AppInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Resource
    private AppInfoMapper appInfoMapper;


    @Override
    public int insert(AppInfo appInfo) {


        return appInfoMapper.insert(appInfo);
    }


    @Override
    public int delete(int id) {
        return appInfoMapper.delete(id);
    }


    @Override
    public int update(AppInfo appInfo) {
        return appInfoMapper.update(appInfo);
    }

    @Override
    public AppInfo selectByUnionKey(String platform, String appType) {
        if (StringUtils.isBlank(platform) || StringUtils.isBlank(appType)) {
            return null;
        }
        return appInfoMapper.selectByUnionKey(platform, appType);
    }

}
