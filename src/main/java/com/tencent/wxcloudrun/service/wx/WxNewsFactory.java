package com.tencent.wxcloudrun.service.wx;

import com.tencent.wxcloudrun.Enum.WxNewsEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WxNewsFactory implements InitializingBean {
    @Autowired
    private List<WxNewsHandler> wxNewsServiceList;

    private Map<WxNewsEnum, WxNewsHandler> handlerMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        handlerMap = new HashMap<>();
        wxNewsServiceList.forEach(p -> {
            handlerMap.put(p.type(), p);
        });
    }

    public WxNewsHandler getHandler(WxNewsEnum wxNewsEnum) {
        if (wxNewsEnum == null) {
            return handlerMap.get(WxNewsEnum.VIEW);
        }
        return handlerMap.get(wxNewsEnum);
    }
}
