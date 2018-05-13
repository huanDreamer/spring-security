package top.sillyfan.browser.validator.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.sillyfan.security.constants.Operator;

import java.util.List;

@Component
public class SmsSenderHelper {

    @Autowired
    private List<SmsSender> smsSenders;
    
    public void sendSms(String phoneNum, String message) {

        Operator operator = analysisOperator(phoneNum);

        for (SmsSender smsSender : smsSenders) {
            if (operator.equals(smsSender.supportedOperator())) {
                smsSender.sendSms(phoneNum, message);
                return;
            }
        }
        // throwException
    }


    /**
     * 解析手机号码对应的运营商
     *
     * @param phoneNum
     * @return
     */
    private Operator analysisOperator(String phoneNum) {
        // 使用运营商决策类，调用不同运营商的判断方法，获取号码所属的运营商

        // 本地直接返回Default
        return Operator.Default;
    }
}
