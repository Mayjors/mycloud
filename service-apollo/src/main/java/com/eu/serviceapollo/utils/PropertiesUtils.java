package com.eu.serviceapollo.utils;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

import java.util.Properties;

/**
 * 独取公共apollo配置
 * @author yuanjie
 * @date 2019/3/12 14:56
 */
public class PropertiesUtils {
    private static final String COMMON = "nova1.NovaCommon";
    public static Properties properties = new Properties();
    static {
        Config commonConfig = ConfigService.getConfig(COMMON);
        if (commonConfig != null) {
            for (String key : commonConfig.getPropertyNames()) {
                properties.setProperty(key, commonConfig.getProperty(key, null));
            }
        }
    }
}
