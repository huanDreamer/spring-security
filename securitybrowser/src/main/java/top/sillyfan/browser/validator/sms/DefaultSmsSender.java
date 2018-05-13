package top.sillyfan.browser.validator.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.sillyfan.security.constants.Operator;

/**
 * 默认的短信方法送客户端：打印到控制台
 */
@Component
public class DefaultSmsSender implements SmsSender {

    private static final Logger logger = LoggerFactory.getLogger(DefaultSmsSender.class);

    @Override
    public void sendSms(String phoneNum, String message) {

        logger.info("发送短信验证码到 [{}], 验证码是 [{}]", phoneNum, message);
    }

    @Override
    public Operator supportedOperator() {
        return Operator.Default;
    }

}
