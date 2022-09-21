package com.tencent.wxcloudrun.service.impl.wx;

import com.tencent.wxcloudrun.Enum.WxNewsEnum;
import com.tencent.wxcloudrun.config.ThreadLocalConfig;
import com.tencent.wxcloudrun.constant.WxConstant;
import com.tencent.wxcloudrun.dto.ContextInfo;
import com.tencent.wxcloudrun.dto.wx.WxNewsInfo;
import com.tencent.wxcloudrun.model.WxUserInfo;
import com.tencent.wxcloudrun.service.WxUserInfoService;
import com.tencent.wxcloudrun.service.wx.WxNewsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 取消关注事件
 */
@Service("wxNewsUnSubScribeHandler")
public class WxNewsUnSubScribeHandler extends AbstractWxNewsHandler implements WxNewsHandler {
    @Autowired
    private WxUserInfoService wxUserInfoService;
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
        if (userInfo != null) {
            userInfo.setSubscribe(Integer.valueOf(WxNewsEnum.UNSUBSCRIBE.getDataCode()));
            wxUserInfoService.updateById(userInfo);
        }
        return buildTxtResult(result, news);
    }

    @Override
    public WxNewsEnum type() {
        return WxNewsEnum.UNSUBSCRIBE;
    }
}
