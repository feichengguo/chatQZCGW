package com.tencent.wxcloudrun.config;

import com.tencent.wxcloudrun.dto.ContextInfo;
import org.springframework.stereotype.Component;

@Component
public class ThreadLocalConfig {
    private static ThreadLocal<ContextInfo> threadLocal = new ThreadLocal<>();

    //获取当前线程的存的变量
    public ContextInfo get() {
        return threadLocal.get();
    }

    //设置当前线程的存的变量
    public void set(ContextInfo contextInfo) {
        this.threadLocal.set(contextInfo);
    }

    //移除当前线程的存的变量
    public void remove() {
        this.threadLocal.remove();
    }
}
