package top.sillyfan.browser.validator.code;

import java.time.LocalDateTime;

/**
 * 短信验证码
 */
public class SmsCode extends AbstractValidatorCode{

    public SmsCode(String code, Long expireIn) {
        super(code, expireIn);
    }
}
