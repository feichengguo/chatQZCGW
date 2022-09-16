package com.tencent.wxcloudrun.Enum;

import java.util.HashMap;
import java.util.Map;

public enum WxNewsEnum {
    SUBSCRIBE("subscribe", "1", ""),
    UNSUBSCRIBE("unsubscribe", "0", "取消关注"),
    VIEW("VIEW", "VIEW", "点击菜单跳转链接时的事件推送"),
    CLICK("CLICK", "CLICK", "点击菜单拉取消息时的事件推送"),
    TEXT("text", "text", "文本事件推送"),
    ;

    private String wxCode;
    private String dataCode;
    private String desc;
    private static Map<String, WxNewsEnum> wxMap = new HashMap<>();

    static {
        for (WxNewsEnum value : WxNewsEnum.values()) {
            wxMap.put(value.getWxCode(), value);
        }
    }

    public static WxNewsEnum getByCode(String code) {
        return wxMap.get(code);
    }

    WxNewsEnum(String wxCode, String dataCode, String desc) {
        this.wxCode = wxCode;
        this.dataCode = dataCode;
        this.desc = desc;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
