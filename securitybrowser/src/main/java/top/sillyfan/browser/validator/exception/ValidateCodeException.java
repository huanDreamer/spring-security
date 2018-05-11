package top.sillyfan.browser.validator.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码验证异常
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
