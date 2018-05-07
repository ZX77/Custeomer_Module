package common;

import java.io.InputStream;
import java.util.Properties;

//加载配置文件
public class WeChatProperties {
	private static Properties properties;

    private static synchronized void loadProperties() {
        if (null == properties) {
            try {
            	Properties prop = new Properties();
                InputStream inputStream = WeChatProperties.class.getClassLoader().getResourceAsStream("system.properties");
                prop.load(inputStream);
                properties = prop;
            } catch (Exception e) {
                throw new RuntimeException("未找到system.properties配置文件.");
            }
        }
    }

    //通过参数名称获取值
    public static String get(String key) {
        loadProperties();
        return properties.getProperty(key);
    }
}
