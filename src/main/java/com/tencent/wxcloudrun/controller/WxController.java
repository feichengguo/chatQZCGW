package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.constant.WxConstant;
import com.tencent.wxcloudrun.service.aes.AesException;
import com.tencent.wxcloudrun.service.wx.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/wx/qzcgt")
public class WxController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private WxService wxService;

    /**
     * 微信公众平台服务器地址URL服务
     * 公众号-基本配置-服务器配置-服务器地址(URL)：http://zrj.free.idcfengye.com/wechat/token/security
     * https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Access_Overview.html
     * 调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。
     * <p>
     * 测试号地址配置
     * 公众号-开发工具-公众平台测试账号-测试号管理-接口配置信息(URL)：http://zrj.free.idcfengye.com/wx/qzcgt/event
     * https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
     *
     * @param signature    微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp    时间戳
     * @param nonce        随机数
     * @param echostr      随机字符串
     * @param encryptType  encrypt_type（加密类型，为 aes）
     * @param msgSignature msg_signature（消息体签名，用于验证消息体的正确性）
     * @param openid       openid类似于公众号关注用户id
     * @param postData     消息体
     * @return java.lang.String
     */
    @RequestMapping(value = "/event")
    public String event(@RequestParam("signature") String signature,
                        @RequestParam("timestamp") String timestamp,
                        @RequestParam("nonce") String nonce,
                        @RequestParam(name = "echostr", required = false) String echostr,
                        @RequestParam(name = "encrypt_type", required = false) String encryptType,
                        @RequestParam(name = "msg_signature", required = false) String msgSignature,
                        @RequestParam(name = "openid", required = false) String openid,
                        @RequestBody(required = false) String postData) {
        long beginTime = System.currentTimeMillis();
        LOGGER.info("event begin");

        String result = null;
        try {
            result = wxService.event(signature, timestamp, nonce, echostr, encryptType, msgSignature, openid, postData);
        } catch (AesException e) {
            LOGGER.error("event error", e);
            result = WxConstant.SUCCESS;
        }


        long endTime = System.currentTimeMillis();
        LOGGER.info("event end ==消耗时间==" + (endTime - beginTime) + "ms");
        return result;
    }


}
