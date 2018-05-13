package top.sillyfan.browser.validator.sms;

import top.sillyfan.security.constants.Operator;

/**
 * 短信发送接口
 */
public interface SmsSender {

    /**
     * 短信发送方法 不同的短信服务商提供的不一样
     *
     * @param phoneNum
     * @param message
     */
    void sendSms(String phoneNum, String message);

    /**
     * 获取支持的运营商平台
     *
     * @return
     */
    Operator supportedOperator();
}
