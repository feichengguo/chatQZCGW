package com.tencent.wxcloudrun.service.thread;

import com.tencent.wxcloudrun.model.WxUserInfo;

public interface AsyncService {
    /** * 执行异步任务 * 可以根据需求，自己加参数拟定，我这里就做个测试演示 */
    void executeAsync( WxUserInfo userInfo );
}
