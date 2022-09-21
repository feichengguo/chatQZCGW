package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.AppInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AppInfoMapper {
    /**
     * 新增
     *
     * @author gcf
     * @date 2022/09/21
     **/
    int insert(AppInfo appInfo);

    /**
     * 刪除
     *
     * @author gcf
     * @date 2022/09/21
     **/
    int delete(int id);

    /**
     * 更新
     *
     * @author gcf
     * @date 2022/09/21
     **/
    int update(AppInfo appInfo);

    AppInfo selectByUnionKey(@Param("platform") String platform,
                             @Param("appType") String appType);

}
