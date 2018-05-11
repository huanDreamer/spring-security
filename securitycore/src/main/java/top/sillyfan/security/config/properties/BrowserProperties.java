package top.sillyfan.security.config.properties;

public class BrowserProperties {

    // 如果没有配置就是用默认值
    private String loginPage = "login.html";

    private String SessionKey = "session_image_code";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getSessionKey() {
        return SessionKey;
    }

    public void setSessionKey(String sessionKey) {
        SessionKey = sessionKey;
    }
}
