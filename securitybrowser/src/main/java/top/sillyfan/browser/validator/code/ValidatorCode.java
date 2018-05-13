package top.sillyfan.browser.validator.code;

/**
 * 验证码接口
 */
public interface ValidatorCode {

    /**
     * 获取生成的code
     *
     * @return
     */
    String getCode();

    /**
     * 验证码是否过期
     *
     * @return
     */
    Boolean isExpired();
}
