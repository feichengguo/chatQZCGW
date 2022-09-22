package wx;

import com.tencent.wxcloudrun.Enum.WxZghEnum;
import com.tencent.wxcloudrun.config.ThreadLocalConfig;
import com.tencent.wxcloudrun.dto.ContextInfo;
import com.tencent.wxcloudrun.dto.wx.WxEntranceRequest;
import com.tencent.wxcloudrun.model.AppInfo;
import com.tencent.wxcloudrun.model.WxUserInfo;
import com.tencent.wxcloudrun.service.entity.AppInfoService;
import com.tencent.wxcloudrun.service.impl.wx.WxEventEntranceServiceImpl;
import com.tencent.wxcloudrun.service.thread.AsyncService;
import com.tencent.wxcloudrun.utils.RedisUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {com.tencent.wxcloudrun.WxCloudRunApplication.class})
public class WxEventEntranceServiceTest {
    @Resource
    private WxEventEntranceServiceImpl wxEventEntranceService;
    @Resource
    private AppInfoService appInfoService;
    @Autowired
    ThreadLocalConfig threadLocalConfig;

    @Resource(name = "asyncWxServiceImpl")
    private AsyncService asyncWxService;

    @Autowired
    private RedisUtil redisUtil;

    @Before
    public void init() {
        ContextInfo contextInfo = new ContextInfo();
        contextInfo.setPlatform(WxZghEnum.CODE_1.getWxPlatform());
        contextInfo.setAppType(WxZghEnum.CODE_1.getAppType());
        threadLocalConfig.set(contextInfo);

    }

    @Test
    public void testSubScribeHandler() {
        WxEntranceRequest request = new WxEntranceRequest();
        request.setData("<xml>\n" +
                "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[FromUser]]></FromUserName>\n" +
                "  <CreateTime>123456789</CreateTime>\n" +
                "  <MsgType><![CDATA[event]]></MsgType>\n" +
                "  <Event><![CDATA[subscribe]]></Event>\n" +
                "</xml>\n");
        request.setVerifyUrlFlag("0");
        wxEventEntranceService.executeNews(request);
    }

    @Test
    public void testUnSubScribeHandler() {
        WxEntranceRequest request = new WxEntranceRequest();
        request.setData("<xml>\n" +
                "  <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[FromUser]]></FromUserName>\n" +
                "  <CreateTime>123456789</CreateTime>\n" +
                "  <MsgType><![CDATA[event]]></MsgType>\n" +
                "  <Event><![CDATA[unsubscribe]]></Event>\n" +
                "</xml>\n");
        request.setVerifyUrlFlag("0");
        wxEventEntranceService.executeNews(request);
    }

    @Test
    public void testAppInfoService() {
        AppInfo appInfo = appInfoService.selectByUnionKey("QZCGT", "public");
        System.out.printf(appInfo.getAppId());
    }


    @Test
    public void testAsyn() {
        WxUserInfo userInfo = new WxUserInfo();

        asyncWxService.executeAsync(userInfo);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRedis() {
        redisUtil.setex("key1", "1", 2);

        System.out.println(redisUtil.get("key1"));
        try {
            Thread.sleep(1002);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisUtil.setex("key1", "2", 2);
        try {
            Thread.sleep(1002);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(redisUtil.get("key1"));

    }

}
