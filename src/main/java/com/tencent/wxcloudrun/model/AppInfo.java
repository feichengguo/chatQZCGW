package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AppInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;

    /**
     * 公众号类型
     */
    private String platform;

    /**
     * 应用类型 public-公众号;applet-小程序
     */
    private String appType;

    /**
     * app_id
     */
    private String appId;

    /**
     * app_secret
     */
    private String appSecret;

    /**
     * client_credential
     */
    private String clientCredential;

    /**
     * aes_key
     */
    private String aesKey;

    /**
     * aes_token
     */
    private String aesToken;

    /**
     * 描述
     */
    private String appDesc;

    /**
     * created
     */
    private Date created;

    /**
     * updated
     */
    private Date updated;
}
