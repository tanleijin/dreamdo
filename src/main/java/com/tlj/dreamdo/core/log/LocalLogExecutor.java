package com.tlj.dreamdo.core.log;

/**
* 实现此接口将日志同步到数据库
* @author tanleijin
* @date 2019/9/6 15:57
*/
public interface LocalLogExecutor {

    /**
     * 将日志同步到数据库
      *
      * @param @param sysLog    参数
      * @return void    返回类型
      * @throws
     */
    void saveLog(ISysLogTemplate sysLog);
    
}
