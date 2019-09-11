package com.tlj.dreamdo.core.security.handler;

import com.tlj.dreamdo.common.util.JsonUtils;
import com.tlj.dreamdo.common.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* 1.自定义的登陆成功处理  implements  AuthenticationSuccessHandler  Override  onAuthenticationSuccess()
 * 2. 或者继承框架默认实现的成功处理器类 SavedRequestAwareAuthenticationSuccessHandler 重写父类方法onAuthenticationSuccess
* @author tanleijin
* @date 2019/9/10 10:54
*/
@Component
public class DefaultAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LOGGER.info("----login in succcess----");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtils.objectToJson(R.ok()));
    }

}
