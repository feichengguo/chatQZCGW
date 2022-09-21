package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.WxUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WxUserInfoMapper {
    /**
     * 新增
     *
     * @author gcf
     * @date 2022/09/21
     **/
    int insert(WxUserInfo wxUserInfo);

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
    int updateById(WxUserInfo wxUserInfo);

    /**
     * 查询 根据主键 id 查询
     *
     * @author gcf
     * @date 2022/09/21
     **/
    WxUserInfo load(int id);

    /**
     * 根据唯一主键查询
     *
     * @param platform
     * @param appType
     * @param openId
     * @return
     */
    WxUserInfo selectByUnionKey(@Param("platform") String platform,
                                @Param("appType") String appType,
                                @Param("openId") String openId);

}
