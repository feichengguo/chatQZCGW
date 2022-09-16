package com.tencent.wxcloudrun.service.wx;

import com.tencent.wxcloudrun.Enum.WxNewsEnum;
import com.tencent.wxcloudrun.dto.wx.WxNewsInfo;

public interface WxNewsHandler {
    String process(WxNewsInfo news);
    WxNewsEnum type();
}
