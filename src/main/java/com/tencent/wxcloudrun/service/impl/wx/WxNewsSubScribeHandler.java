package com.tencent.wxcloudrun.service.impl.wx;

import com.tencent.wxcloudrun.Enum.WxNewsEnum;
import com.tencent.wxcloudrun.config.ThreadLocalConfig;
import com.tencent.wxcloudrun.constant.WxConstant;
import com.tencent.wxcloudrun.dto.ContextInfo;
import com.tencent.wxcloudrun.dto.wx.WxNewsInfo;
import com.tencent.wxcloudrun.model.WxUserInfo;
import com.tencent.wxcloudrun.service.WxUserInfoService;
import com.tencent.wxcloudrun.service.thread.AsyncService;
import com.tencent.wxcloudrun.service.wx.WxNewsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 关注事件
 */
@Service("wxNewsSubScribeHandler")
public class WxNewsSubScribeHandler extends AbstractWxNewsHandler implements WxNewsHandler {
    @Autowired
    private WxUserInfoService wxUserInfoService;

    @Resource(name = "asyncWxServiceImpl")
    private AsyncService asyncWxService;
    @Autowired
    ThreadLocalConfig threadLocalConfig;

    @Override
    public String process(WxNewsInfo news) {
        String result = "";
        ContextInfo contextInfo = threadLocalConfig.get();
        if (contextInfo == null) {
            return buildTxtResult(result, news);
        }

        WxUserInfo userInfo = wxUserInfoService.selectByUnionKey(contextInfo.getPlatform(), contextInfo.getAppType(), news.getFromUserName());
        if (userInfo != null && !(WxNewsEnum.SUBSCRIBE.getDataCode().equals(userInfo.getSubscribe()))) {
            userInfo.setSubscribe(Integer.valueOf(WxNewsEnum.SUBSCRIBE.getDataCode()));
            wxUserInfoService.updateById(userInfo);
        } else {
            userInfo = new WxUserInfo();
            userInfo.setPlatform(contextInfo.getPlatform());
            userInfo.setAppType(contextInfo.getAppType());
            userInfo.setOpenId(news.getFromUserName());
            userInfo.setSubscribe(Integer.valueOf(WxNewsEnum.SUBSCRIBE.getDataCode()));
            wxUserInfoService.insert(userInfo);

            asyncWxService.executeAsync(userInfo);
        }

        return buildTxtResult(result, news);
    }

    @Override
    public WxNewsEnum type() {
        return WxNewsEnum.SUBSCRIBE;
    }
}
