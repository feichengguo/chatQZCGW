package com.tencent.wxcloudrun.service.impl.wx;

import com.tencent.wxcloudrun.Enum.WxNewsEnum;
import com.tencent.wxcloudrun.dto.wx.WxNewsInfo;
import com.tencent.wxcloudrun.service.wx.WxNewsHandler;
import org.springframework.stereotype.Service;

@Service("wxNewsClickHandler")
public class WxNewsClickHandler extends AbstractWxNewsHandler implements WxNewsHandler {
    @Override
    public String process(WxNewsInfo news) {
        String result = "";
        return buildTxtResult(result, news);
    }

    @Override
    public WxNewsEnum type() {
        return WxNewsEnum.CLICK;
    }
}
