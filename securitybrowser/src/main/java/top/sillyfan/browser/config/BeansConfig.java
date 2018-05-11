package top.sillyfan.browser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import top.sillyfan.browser.validator.filter.ValidateCodeFilter;

@Configuration
public class BeansConfig {

    @Bean
    SessionStrategy sessionStrategy() {
        return new HttpSessionSessionStrategy();
    }

}
