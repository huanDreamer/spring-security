package top.sillyfan.security.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sillyfan.security")
public class SecurityProperties {

    private BrowserProperties browser;

    public SecurityProperties() {
        browser = new BrowserProperties();
    }

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
