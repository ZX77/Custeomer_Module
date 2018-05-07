package common;

//读取配置文件中的参数
public class WeChatConfig {
    public static final String URL = WeChatProperties.get("Url");
    public static final String USERNAME = WeChatProperties.get("Username");
    public static final String PASSWORD = WeChatProperties.get("password");
    public static final String DBDRIVER = WeChatProperties.get("Driver");

}
