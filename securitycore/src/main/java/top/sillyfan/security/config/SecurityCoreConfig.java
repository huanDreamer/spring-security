package top.sillyfan.security.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("top.sillyfan.security")
@MapperScan("top.sillyfan.security.domain.repository")
public class SecurityCoreConfig {
}
