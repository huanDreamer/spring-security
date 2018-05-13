package top.sillyfan.browser.validator.generator;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import top.sillyfan.browser.validator.code.SmsCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信验证码生成器
 */
@Component
public class SmsValidatorCodeGenerator implements ValidatorCodeGenerator<SmsCode> {

    // 短信验证码长度
    private Integer codeLength = 4;

    @Override
    public SmsCode generatorCode(HttpServletRequest request, HttpServletResponse response) {
        String code = RandomStringUtils.randomNumeric(codeLength);

        // 有效期为 60s
        return new SmsCode(code, 60L);
    }
}
