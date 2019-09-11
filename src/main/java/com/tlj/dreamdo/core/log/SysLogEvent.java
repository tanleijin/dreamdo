package com.tlj.dreamdo.core.log;

import org.springframework.context.ApplicationEvent;

/**
* @Description: 系统日志事件
* @author tanleijin
* @date 2019/9/6 15:58
*/
public class SysLogEvent extends ApplicationEvent {
    
    private static final long serialVersionUID = -5421370820213016057L;

    /** 
     * <p>Title: </p> 
     * <p>Description: </p> 
     * @param sysLog 
     */
    public SysLogEvent(ISysLogTemplate sysLog) {
        super(sysLog);
    }

}
