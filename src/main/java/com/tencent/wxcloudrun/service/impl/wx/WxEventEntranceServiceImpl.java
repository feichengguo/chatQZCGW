package com.tencent.wxcloudrun.service.impl.wx;

import com.tencent.wxcloudrun.Enum.WxNewsEnum;
import com.tencent.wxcloudrun.constant.WxConstant;
import com.tencent.wxcloudrun.dto.wx.WxEntranceRequest;
import com.tencent.wxcloudrun.dto.wx.WxEntranceResponse;
import com.tencent.wxcloudrun.dto.wx.WxNewsInfo;
import com.tencent.wxcloudrun.service.wx.WxEventEntranceService;
import com.tencent.wxcloudrun.service.wx.WxNewsFactory;
import com.tencent.wxcloudrun.service.wx.WxNewsHandler;
import com.tencent.wxcloudrun.utils.XmlBuilderUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxEventEntranceServiceImpl implements WxEventEntranceService {
    @Autowired
    private WxNewsFactory wxNewsFactory;

    @Override
    public WxEntranceResponse executeNews(WxEntranceRequest request) {
        WxEntranceResponse response = new WxEntranceResponse();
        if ("1".equals(request.getVerifyUrlFlag())) {
            response.setResult(request.getData());
            response.setVerifyUrlFlag("1");
            return response;
        }

        if (StringUtils.isNotBlank(request.getData())) {

            WxNewsInfo news = XmlBuilderUtil.xmlStrToObject(WxNewsInfo.class, request.getData());

            String result = WxConstant.SUCCESS;

            if (news != null) {
                WxNewsEnum wxNewsEnum = WxNewsEnum.getByCode(news.getEvent());

                WxNewsHandler handler = wxNewsFactory.getHandler(wxNewsEnum);

                result = handler.process(news);
            }


            response.setResult(result);
        }
        return response;
    }
}
