package com.tlj.dreamdo.core.log;

import java.lang.annotation.*;

/**
* 记录系统日志
* @author tanleijin
* @date 2019/9/6 15:58
*/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";
}
