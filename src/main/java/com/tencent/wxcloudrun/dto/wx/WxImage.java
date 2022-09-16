package com.tencent.wxcloudrun.dto.wx;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"mediaId",})
public class WxImage {
    @XmlElement(name = "MediaId")
    private String mediaId;
}
