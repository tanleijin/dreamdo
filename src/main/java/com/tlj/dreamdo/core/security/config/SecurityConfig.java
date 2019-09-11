package com.tlj.dreamdo.core.security.config;

import com.tlj.dreamdo.core.security.config.properties.SecurityProperties;
import com.tlj.dreamdo.system.util.GetMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tanleijin
 * @description (Security配置文件)
 * @Date:2019/9/6 16:01
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启方法权限控制
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityProperties securityProperties;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler defaultAuthenticationFailureHandler;

    /**
     * 记住我实现
     */
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        // 该对象里面有定义创建表的语句
        // 可以设置让该类来创建表(persistent_logins)
        jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
        return jdbcTokenRepositoryImpl;
    }

    /**
     * 加密方式
     */
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
    * @Description: 主配置方法2
    * @author tanleijin
    * @date 2019/9/10 15:03
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //4.配置自己实现的登录认证的service,并设置密码的加密方式（）
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
    * @Description: 主配置方法1
    * @author tanleijin
    * @date 2019/9/10 15:03
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //1.表单登录
        http.formLogin()
                .loginPage(securityProperties.getLoginUrl())//后台登录的页面
                .loginProcessingUrl(securityProperties.getLoginProcessingUrl()) //登录请求拦截的url,也就是form表单提交时指定的action
                .usernameParameter(securityProperties.getUsernameParameter())  //用户名的请求字段 默认为userName
                .passwordParameter(securityProperties.getPasswordParameter())  //密码的请求字段 默认为password
                .successHandler(defaultAuthenticationSuccessHandler)
                .failureHandler(defaultAuthenticationFailureHandler)
                .and()
            //2.配置过滤请求
            .authorizeRequests()
            .antMatchers(getUrls()).permitAll()//不拦截请求
            .and()
            .csrf().disable()//禁用csrf
            //3.记住我(当需要记住我的)
            //.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getRememberMeSeconds())// 有效期20天
                .and()
            //4.配置自己实现的登录认证的service(此步骤移到主配置方法2中配置)
            //.userDetailsService(userDetailsService)
            //5.配置session管理
            .sessionManagement()
                .invalidSessionUrl(securityProperties.getLoginUrl())//session失效默认的跳转地址
                .maximumSessions(securityProperties.getMaximumSessions())//最大的并发数
                .maxSessionsPreventsLogin(securityProperties.isMaxSessionsPreventsLogin());//之后的登录是否踢掉之前的登录
            //    .expiredSessionStrategy(sessionInformationExpiredStrategy)
            //    .and()
            //   .and()
            //6.登出
            /*.logout()
                .invalidateHttpSession(true)
                .logoutUrl(securityProperties.getLogout())
                .deleteCookies(securityProperties.getJsessionid());
                //.logoutSuccessHandler(logoutSuccessHandler);*/

    }
    /**
     * 获取IndexController，ArticleController，UserDataController中的不需要拦截的请求
     * @return
     */
    private String[] getUrls() {
        List<String> urls = getUrlList();
        urls.addAll(GetMapperUtil.getMapperValue());
        return urls.stream().toArray(String[]::new);
    }
    /**
     * 获取一些静态的不需要拦截的请求
     * @return
     */
    private List<String> getUrlList(){
        List<String> urls = new ArrayList<>();
        urls.add("/favicon.ico");
        urls.add("/webjars/**");
        urls.add("/asserts/**");
        urls.add("/images/**");
        return urls;
    }
}
