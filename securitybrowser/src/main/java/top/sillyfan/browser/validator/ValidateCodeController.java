package top.sillyfan.browser.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import top.sillyfan.browser.validator.code.ImageCode;
import top.sillyfan.browser.validator.code.SmsCode;
import top.sillyfan.browser.validator.generator.ImageValidatorCodeGenerator;
import top.sillyfan.browser.validator.generator.SmsValidatorCodeGenerator;
import top.sillyfan.browser.validator.sms.SmsSenderHelper;
import top.sillyfan.security.config.properties.SecurityProperties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ValidateCodeController {

    @Autowired
    private SecurityProperties securityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ImageValidatorCodeGenerator imageValidatorCodeGenerator;

    @Autowired
    private SmsValidatorCodeGenerator smsValidatorCodeGenerator;

    @Autowired
    private SmsSenderHelper smsSenderHelper;

    /**
     * 生成图形验证码：
     * <p>
     * 1. 根据随机数生成图片
     * <p>
     * 2. 将随机数存到session
     * <p>
     * 3. 将图片写到response
     *
     * @param request
     * @param response
     */
    @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageCode imageCode = imageValidatorCodeGenerator.generatorCode(request, response);

        sessionStrategy.setAttribute(new ServletWebRequest(request), securityProperties.getBrowser().getSessionKey(), imageCode);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }


    /**
     * 创建短信的验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {

        SmsCode smsCode = smsValidatorCodeGenerator.generatorCode(request, response);

        sessionStrategy.setAttribute(new ServletWebRequest(request), securityProperties.getBrowser().getSessionKey(), smsCode);

        // 获取电话号码
        String phoneNUm = ServletRequestUtils.getStringParameter(request, "phoneNum");

        // 将短信发送到用户手机
        smsSenderHelper.sendSms(phoneNUm, smsCode.getCode());
    }
}
