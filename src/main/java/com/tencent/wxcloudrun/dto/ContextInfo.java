package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class ContextInfo {
    /**
     * 公众号类型
     */
    private String platform;

    /**
     * 应用类型 public-公众号;applet-小程序
     */
    private String appType;
}
