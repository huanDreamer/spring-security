package top.sillyfan.browser.validator.code;

import java.awt.image.BufferedImage;

/**
 * 图形验证码
 */
public class ImageCode extends AbstractValidatorCode {

    // 图片
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, Long expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
