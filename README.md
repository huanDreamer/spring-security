# spring-security

spring-security-demo

# spring security 验证流程

```
用户名 密码 
    -> UsernamePasswordAuthenticationFilter -> UsernamePasswordAuthenticationToken(未认证)
        -> AuthenticationManager 
            -> DaoAuthenticationProvider
                -> UserDetailsService
                    -> UserDetails
```
# 自定义短信验证流程

```
手机号 验证码
    -> SmsAuthenticationFilter (自定义Filter) -> SmsAuthenticationToken(未认证)
        -> AuthenticationManager
            -> SmsAuthenticationProvider (自定义Provider)
                -> UserDetailsService
                    -> Authentication (认证通过)
```