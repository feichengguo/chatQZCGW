package com.tencent.wxcloudrun.service.impl.wx;

import com.tencent.wxcloudrun.Enum.WxNewsEnum;
import com.tencent.wxcloudrun.constant.WxConstant;
import com.tencent.wxcloudrun.dto.wx.WxNewsInfo;
import com.tencent.wxcloudrun.service.wx.WxNewsHandler;
import org.springframework.stereotype.Service;

@Service("wxNewsVIewHandler")
public class WxNewsVIewHandler extends AbstractWxNewsHandler implements WxNewsHandler {
    @Override
    public String process(WxNewsInfo news) {
        return WxConstant.SUCCESS;
    }

    @Override
    public WxNewsEnum type() {
        return WxNewsEnum.VIEW;
    }
}
