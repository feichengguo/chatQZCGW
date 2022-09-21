package com.tencent.wxcloudrun.Enum;

public enum WxZghEnum {
    CODE_1("QZCGT","public","旗帜财观团");

    private String wxPlatform;
    private String appType;
    private String desc;

    public String getWxPlatform() {
        return wxPlatform;
    }

    public void setWxPlatform(String wxPlatform) {
        this.wxPlatform = wxPlatform;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    WxZghEnum(String wxPlatform, String appType, String desc) {
        this.wxPlatform = wxPlatform;
        this.appType = appType;
        this.desc = desc;
    }
}
