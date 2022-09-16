package com.tencent.wxcloudrun.service.wx;

import com.tencent.wxcloudrun.dto.wx.WxEntranceRequest;
import com.tencent.wxcloudrun.dto.wx.WxEntranceResponse;

public interface WxEventEntranceService {
    WxEntranceResponse executeNews(WxEntranceRequest request);
}
