package top.sillyfan.security.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.sillyfan.browser.config.BrowserSecurityConfig;
import top.sillyfan.security.controller.filter.TimeFilter;

import java.util.Collections;

@Configuration
@Import(BrowserSecurityConfig.class)
public class WebConfig {

//    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();

        registrationBean.setFilter(timeFilter);

        registrationBean.setUrlPatterns(Collections.singletonList("/*"));

        return registrationBean;
    }
}
