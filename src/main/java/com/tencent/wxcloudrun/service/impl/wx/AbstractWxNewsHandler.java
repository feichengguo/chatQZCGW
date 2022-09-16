package com.tencent.wxcloudrun.service.impl.wx;

import com.tencent.wxcloudrun.Enum.WxNewsEnum;
import com.tencent.wxcloudrun.dto.wx.WxNewsInfo;
import com.tencent.wxcloudrun.dto.wx.WxResultInfo;
import com.tencent.wxcloudrun.utils.XmlBuilderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AbstractWxNewsHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractWxNewsHandler.class);

    protected String buildTxtResult(String content, WxNewsInfo newsInfo) {
        WxResultInfo resultInfo = new WxResultInfo();
        resultInfo.setContent(content);
        resultInfo.setCreateTime(Calendar.getInstance().getTimeInMillis());
        resultInfo.setFromUserName(newsInfo.getToUserName());
        resultInfo.setToUserName(newsInfo.getFromUserName());
        resultInfo.setMsgType(WxNewsEnum.TEXT.getWxCode());
        String result = XmlBuilderUtil.convertToXML(resultInfo);
        return result;

    }
}
