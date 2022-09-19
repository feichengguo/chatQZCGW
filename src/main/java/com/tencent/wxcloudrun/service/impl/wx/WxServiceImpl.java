package com.tencent.wxcloudrun.service.impl.wx;

import com.tencent.wxcloudrun.constant.WxConstant;
import com.tencent.wxcloudrun.controller.WxController;
import com.tencent.wxcloudrun.dto.wx.WxEntranceRequest;
import com.tencent.wxcloudrun.dto.wx.WxEntranceResponse;
import com.tencent.wxcloudrun.service.aes.AesException;
import com.tencent.wxcloudrun.service.aes.WXBizMsgCrypt;
import com.tencent.wxcloudrun.service.wx.WxEventEntranceService;
import com.tencent.wxcloudrun.service.wx.WxService;
import com.tencent.wxcloudrun.utils.XmlBuilderUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;

@Service
public class WxServiceImpl implements WxService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WxServiceImpl.class);
    //第三方用户唯一凭证
    public static final String APP_ID = "wx1xxxxxx441";
    //公众号 令牌(Token)，可以优化到配置中心，这个是自定义的，与公众平台配置一致即可
    public static final String MP_TOKEN = "zrj";
    //公众号 消息加解密密钥 EncodingAESKey
    public static final String MP_ENCODING_AES_KEY = "tVNZZP2WuEJxxxxxxAcnZxUAYHvKbl6";

    @Autowired
    private WxEventEntranceService wxEventEntranceService;

    /**
     * 微信公众平台消息和事件推送接收服务
     * 微信服务器在五秒内收不到响应会断掉连接，并且重新发起请求，总共重试三次
     * 如果开发者希望增强安全性，可以在开发者中心处开启消息加密，这样，用户发给公众号的消息以及公众号被动回复用户消息都会继续加密，详见被动回复消息加解密说明。
     * 假如服务器无法保证在五秒内处理并回复，必须做出下述回复，这样微信服务器才不会对此作任何处理，并且不会发起重试（这种情况下，可以使用客服消息接口进行异步回复），否则，将出现严重的错误提示。详见下面说明：
     * 1、直接回复success（推荐方式） 2、直接回复空串（指字节长度为0的空字符串，而不是XML结构体中content字段的内容为空）
     * 一旦遇到以下情况，微信都会在公众号会话中，向用户下发系统提示“该公众号暂时无法提供服务，请稍后再试”：
     * 1、开发者在5秒内未回复任何内容 2、开发者回复了异常数据，比如JSON数据等
     * 另外，请注意，回复图片（不支持gif动图）等多媒体消息时需要预先通过素材管理接口上传临时素材到微信服务器，可以使用素材管理中的临时素材，也可以使用永久素材。
     * 注意：测试中3秒故障提示，“该公众号暂时无法提供服务，请稍后再试”，直接返回"success"，无异常。
     *
     * @param signature    微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数
     * @param timestamp    时间戳
     * @param nonce        随机数
     * @param echostr      随机字符串
     * @param encryptType
     * @param msgSignature
     * @param openid
     * @param postData     消息体
     * @return 如果获得只需要返回 SUCCESS
     */
    @Override
    public String event(String signature, String timestamp, String nonce, String echostr, String encryptType, String msgSignature, String openid, String postData) throws AesException {
        LOGGER.info("【微信公众平台消息事件接收服务】请求参数：signature：【{}】，timestamp：【{}】，nonce：【{}】，echostr：【{}】，encryptType：【{}】，msgSignature：【{}】，openid：【{}】，postData：【{}】", signature, timestamp, nonce, echostr, encryptType, msgSignature, openid, postData);

        //1.配置url验证
        if (StringUtils.isEmpty(postData)) {
            LOGGER.info("【微信公众平台消息事件接收服务】消息体为空直接返回验证成功，返回随机数，echostr：【{}】", echostr);
            return echostr;
        }

        //2.接收事件消息

        //方式一
        //假如服务器无法保证在五秒内处理并回复，必须做出下述回复，这样微信服务器才不会对此作任何处理，并且不会发起重试（这种情况下，可以使用客服消息接口进行异步回复），否则，将出现严重的错误提示。
        //1、直接回复success（推荐方式） 2、直接回复空串（指字节长度为0的空字符串，而不是XML结构体中content字段的内容为空）
        //测试中3秒故障提示，“该公众号暂时无法提供服务，请稍后再试”，直接返回"success"，无异常
        //if (true) {
        //    return "success";
        //}

        //方式二：正常返回，超时会提示用户：该公众号提供的服务出现故障，请稍后再试
        //这个类是微信官网提供的解密类,需要用到消息校验Token 消息加密Key和服务平台appid
        WXBizMsgCrypt pc = new WXBizMsgCrypt(MP_TOKEN, MP_ENCODING_AES_KEY, APP_ID);
        //加密模式：需要解密
        String xml = pc.decryptMsg(msgSignature, timestamp, nonce, postData);

        WxEntranceRequest request = new WxEntranceRequest();
        request.setVerifyUrlFlag("0");
        request.setData(xml);
        WxEntranceResponse response = wxEventEntranceService.executeNews(request);
        LOGGER.info("【微信公众平台消息事件接收服务】解密后消息：【{}】", xml);


        LOGGER.info("【微信公众平台消息事件接收服务成功】消息回复 原文：{}", response.getResult());
        String returnResult = pc.encryptMsg(response.getResult(), String.valueOf(Calendar.getInstance().getTimeInMillis()), nonce);
        LOGGER.info("【微信公众平台消息事件接收服务成功】消息回复 密文：{}", response.getResult());
        return returnResult;

    }
}
