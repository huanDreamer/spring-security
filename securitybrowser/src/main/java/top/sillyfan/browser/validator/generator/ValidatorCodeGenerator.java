package top.sillyfan.browser.validator.generator;

import top.sillyfan.browser.validator.code.ValidatorCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码生成器
 *
 * @param <T>
 */
public interface ValidatorCodeGenerator<T extends ValidatorCode> {

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @return
     */
    T generatorCode(HttpServletRequest request, HttpServletResponse response);
}
