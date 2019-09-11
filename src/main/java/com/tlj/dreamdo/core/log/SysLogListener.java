package com.tlj.dreamdo.core.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
* 注解形式的监听 异步监听日志事件
* @author tanleijin
* @date 2019/9/6 15:59
*/
@ConditionalOnBean(LocalLogExecutor.class)
@Component
public class SysLogListener {


    @Autowired
    private LocalLogExecutor localLogExecutor;
    
    @Async
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        ISysLogTemplate sysLog = (ISysLogTemplate)event.getSource();
        localLogExecutor.saveLog(sysLog);
    }
    
}