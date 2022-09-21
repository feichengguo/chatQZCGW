package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.AppInfo;

public interface AppInfoService {
    /**
     * 新增
     */
    public int insert(AppInfo appInfo);

    /**
     * 删除
     */
    public int delete(int id);

    /**
     * 更新
     */
    public int update(AppInfo appInfo);

    /**
     * 根据业务主键查询
     * @param platform
     * @param appType
     * @return
     */
    public AppInfo selectByUnionKey(String platform, String appType);
}
