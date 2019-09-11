package com.tlj.dreamdo.core.security.handler;

import com.tlj.dreamdo.common.util.JsonUtils;
import com.tlj.dreamdo.common.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* 1.和登录成功处理器是一样的道理
* @author tanleijin
* @date 2019/9/10 11:09
*/
@Component
public class DefaultAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException,
            ServletException {
        LOGGER.info("login in failure : " +  exception.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtils.objectToJson(R.error(exception.getMessage())));
    }
}
