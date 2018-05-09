package top.sillyfan.security.config;

import org.codehaus.jackson.map.ObjectMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.sillyfan.security.config.properties.SecurityProperties;

@Configuration
@ComponentScan("top.sillyfan.security")
@MapperScan("top.sillyfan.security.domain.repository")
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
