package com.tlj.dreamdo.core.security.config.properties;

import org.springframework.stereotype.Component;

/**
 * @Author: tanleijin
 * @description (属性值)
 * @Date:2019/9/6 16:08
 */
@Component
public class SecurityProperties {

    /**
     * 当请求需要身份认证时，默认跳转的url
     */
    private String loginUrl = "/admin/login";
    /**
     * form表单用户名参数名
     */
    private String usernameParameter = "username";

    /**
     * form表单密码参数名
     */
    private String passwordParameter = "password";

    /**
     * 认证的url
     */
    private String loginProcessingUrl = "/security/login";
    private String logout = "/logout";

    private int rememberMeSeconds = 3600 * 24 * 20;
    /**
     * 同一个用户在系统中的最大session数，默认1
     */
    private int maximumSessions = 1;
    /**
     * 达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉
     */
    private boolean maxSessionsPreventsLogin;
    private String jsessionid = "JSESSIONID";

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getUsernameParameter() {
        return usernameParameter;
    }

    public void setUsernameParameter(String usernameParameter) {
        this.usernameParameter = usernameParameter;
    }

    public String getPasswordParameter() {
        return passwordParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        this.passwordParameter = passwordParameter;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public int getMaximumSessions() {
        return maximumSessions;
    }

    public void setMaximumSessions(int maximumSessions) {
        this.maximumSessions = maximumSessions;
    }

    public boolean isMaxSessionsPreventsLogin() {
        return maxSessionsPreventsLogin;
    }

    public void setMaxSessionsPreventsLogin(boolean maxSessionsPreventsLogin) {
        this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
