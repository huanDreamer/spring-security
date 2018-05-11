package top.sillyfan.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.sillyfan.browser.authentication.MyAuthenticationFailHandler;
import top.sillyfan.browser.authentication.MyAuthenticationSuccessHandler;
import top.sillyfan.browser.validator.filter.ValidateCodeFilter;
import top.sillyfan.security.config.SecurityCoreConfig;

/**
 * web 应用的security配置
 */
@Configuration
@ComponentScan("top.sillyfan.browser")
@Import(SecurityCoreConfig.class)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    MyAuthenticationFailHandler authenticationFailHandler;

    @Autowired
    ValidateCodeFilter validateCodeFilter;

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
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)    // 添加自定义的验证码处理器
                .formLogin()    // 表示从登录页面进行授权，如果是formBasic就是alert的形式
                .loginPage("/login")   // 配置登录页面
                .loginProcessingUrl("/form")     // 表示登录表单提交的url {@see UsernamePasswordAuthenticationFilter}
                .successHandler(authenticationSuccessHandler)   // 配置自定义的鉴权成功处理器
                .failureHandler(authenticationFailHandler)      // 鉴权失败的处理器
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/demo-login.html").permitAll()
                .antMatchers("/code/image").permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable() // 把跨站攻击的防护去掉
        ;
    }
}
