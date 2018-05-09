package top.sillyfan.security.config.properties;

public class BrowserProperties {

    // 如果没有配置就是用默认值
    private String loginPage = "login.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
