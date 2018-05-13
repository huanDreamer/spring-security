package top.sillyfan.browser.validator.code;

import java.time.LocalDateTime;

public abstract class AbstractValidatorCode implements ValidatorCode {

    // 随机字符串
    private String code;

    // 过期时间
    private LocalDateTime expireTime;

    protected AbstractValidatorCode(String code, Long expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Boolean isExpired() {
        return expireTime.isBefore(LocalDateTime.now());
    }
}
