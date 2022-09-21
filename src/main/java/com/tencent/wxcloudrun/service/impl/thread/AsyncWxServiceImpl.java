package com.tencent.wxcloudrun.service.impl.thread;

import com.tencent.wxcloudrun.model.WxUserInfo;
import com.tencent.wxcloudrun.service.WxUserInfoService;
import com.tencent.wxcloudrun.service.thread.AsyncService;
import com.tencent.wxcloudrun.service.wx.WxService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("asyncWxServiceImpl")
public class AsyncWxServiceImpl implements AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncWxServiceImpl.class);

    @Autowired
    private WxService wxService;
    @Autowired
    private WxUserInfoService wxUserInfoService;

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(WxUserInfo userInfo) {
        logger.info("start executeAsync");
        if (userInfo.getId() == null) {
            logger.warn("userInfo id is null");
            return;
        }

        String token = wxService.getAccessToken();
        WxUserInfo searchUser = wxService.getUserInfo(userInfo.getOpenId(), token);
        if (searchUser == null) {
            logger.warn("searchUser is null");
            return;
        }
        userInfo.setLanguage(searchUser.getLanguage());
        if (StringUtils.isNotBlank(searchUser.getGroupid())) {
            userInfo.setRemark(searchUser.getGroupid().length() > 8 ? searchUser.getGroupid().substring(0, 8) : searchUser.getGroupid());
        }

        if (StringUtils.isNotBlank(searchUser.getRemark())) {
            userInfo.setRemark(searchUser.getRemark().length() > 32 ? searchUser.getRemark().substring(0, 32) : searchUser.getRemark());
        }

        if (StringUtils.isNotBlank(searchUser.getTagidList())) {
            userInfo.setTagidList(searchUser.getTagidList().length() > 32 ? searchUser.getTagidList().substring(0, 32) : searchUser.getTagidList());
        }

        userInfo.setSubscribeScene(searchUser.getSubscribeScene());

        wxUserInfoService.updateById(userInfo);


        logger.info("end executeAsync");
    }
}
