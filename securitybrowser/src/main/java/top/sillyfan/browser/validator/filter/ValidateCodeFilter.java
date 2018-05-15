package top.sillyfan.browser.validator.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import top.sillyfan.browser.validator.code.ImageCode;
import top.sillyfan.browser.validator.exception.ValidateCodeException;
import top.sillyfan.security.config.properties.SecurityProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器 用于验证输入的验证码是否正确
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    SessionStrategy sessionStrategy;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 登录操作才执行验证逻辑
        if (StringUtils.equals("/authentication/form", httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {

            try {
                validate(new ServletWebRequest(httpServletRequest));
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, exception);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    /**
     * 校验逻辑
     *
     * @param servletWebRequest
     */
    private void validate(ServletWebRequest servletWebRequest) {
        
        String sessionKey = securityProperties.getBrowser().getSessionKey() + "_IMAGE";

        ImageCode imageCode = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, sessionKey);

        String code = "";

        try {
            code = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "code");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("验证码获取错误");
        }

        if (StringUtils.isBlank(code)) {
            throw new ValidateCodeException("请输入验证码");
        }

        if (imageCode == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (imageCode.isExpired()) {
            sessionStrategy.removeAttribute(servletWebRequest, sessionKey);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equalsIgnoreCase(imageCode.getCode(), code)) {
            throw new ValidateCodeException("验证码输入错误");
        }

        sessionStrategy.removeAttribute(servletWebRequest, sessionKey);

    }
}
