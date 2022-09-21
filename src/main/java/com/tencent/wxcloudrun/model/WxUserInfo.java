package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WxUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 平台类型 wx-微信;bd-百度
     */
    private String platform;

    /**
     * 应用类型 public-公众号;applet-小程序
     */
    private String appType;

    /**
     * open_id
     */
    private String openId;

    /**
     * 平台类型 wx-微信;bd-百度
     */
    private String unionId;

    /**
     * 用户是否订阅该公众号标识 0:订阅 1:未订阅
     */
    private Integer subscribe;

    /**
     * 用户语言 zh_cn:简中
     */
    private String language;

    /**
     * 用户所在的分组id（兼容旧的用户分组接口）
     */
    private String groupid;

    /**
     * 关注时间
     */
    private Date subscribeTime;

    /**
     * 公众号运营者对粉丝的备注
     */
    private String remark;

    /**
     * 用户被打上的标签 id 列表
     */
    private String tagidList;

    /**
     * 用户关注的渠道来源，add_scene_search 公众号搜索，add_scene_account_migration 公众号迁移，add_scene_profile_card 名片分享，add_scene_qr_code 扫描二维码，add_scene_profile_link 图文页内名称点击，add_scene_profile_item 图文页右上角菜单，add_scene_paid 支付后关注，add_scene_wechat_advertisement 微信广告，add_scene_reprint 他人转载 ，add_scene_livestream 视频号直播，add_scene_channels 视频号 ， add_scene_others 其他
     */
    private String subscribeScene;

    /**
     * created
     */
    private Date created;

    /**
     * updated
     */
    private Date updated;

}
