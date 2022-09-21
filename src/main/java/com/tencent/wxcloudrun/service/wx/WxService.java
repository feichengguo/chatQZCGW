package com.tencent.wxcloudrun.service.wx;

import com.tencent.wxcloudrun.model.WxUserInfo;
import com.tencent.wxcloudrun.service.aes.AesException;

public interface WxService {
    /**
     * 微信公众平台消息和事件推送接收服务
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @param postData  消息体
     * @return 如果获得只需要返回 SUCCESS
     */
    public String event(String signature, String timestamp, String nonce, String echostr, String encryptType, String msgSignature, String openid, String postData) throws AesException;

    /**
     * 微信公众平台获取AccessToken
     */
    public String getAccessToken();

    /**
     * 获取用户信息
     * @param openId
     * @return
     */
    public WxUserInfo getUserInfo(String openId,  String token);
}
