package top.sillyfan.browser.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import top.sillyfan.security.config.SecurityCoreConfig;

/**
 * web 应用的security配置
 */
@Configuration
@ComponentScan("top.sillyfan.browser")
@Import(SecurityCoreConfig.class)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置密码加密算法
     *
     * @return
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()    // 表示从登录页面进行授权，如果是formBasic就是alert的形式
                .loginPage("/login.html")   // 配置登录页面
                .loginProcessingUrl("/form")     // 表示登录表单提交的url {@see UsernamePasswordAuthenticationFilter}
                .and()
                .authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable() // 把跨站攻击的防护去掉
        ;
    }
}
