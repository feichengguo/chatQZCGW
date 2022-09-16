package com.tencent.wxcloudrun.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;


public class XmlBuilderUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlBuilderUtil.class);

    public static <T> T xmlStrToObject(Class<T> clazz, String xmlStr) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(clazz);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Reader resder = new StringReader(xmlStr);

            return (T) unmarshaller.unmarshal(resder);
        } catch (Exception e) {
            LOGGER.error("XmlBuilderUtil xmlStrToObject", e);

            return null;
        }
    }

    public static <T> String convertToXML(T t) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());

            StringWriter writer = new StringWriter();

            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.marshal(t, writer);

            String xmlStr = writer.toString();

            return StringUtils.replace(xmlStr, "&quot;", "'");
        } catch (JAXBException e) {
            LOGGER.error("XmlBuilderUtil convertToXML", e);
            return null;
        }

    }
}
