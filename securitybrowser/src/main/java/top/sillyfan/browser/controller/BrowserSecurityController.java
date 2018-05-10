package top.sillyfan.browser.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import top.sillyfan.api.response.ApiResponse;
import top.sillyfan.api.response.code.ApiErrorCodeDef;
import top.sillyfan.security.config.properties.SecurityProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(BrowserSecurityController.class);
    @Autowired
    SecurityProperties securityProperties;
    //请求的缓存
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 处理身份认证请求
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/login")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 获取引发登录跳转的请求
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {

            String redirectUrl = savedRequest.getRedirectUrl();

            logger.info("引发跳转的请求：" + redirectUrl);

            //来自网页的登录请求 也可以读取properties的配置
            if (StringUtils.endsWithIgnoreCase(redirectUrl, ".html")) {
                new DefaultRedirectStrategy().sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }

        return ApiResponse.error(ApiErrorCodeDef.CODE_1001);
    }
}
