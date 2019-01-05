/**
 * 
 */
package com.eu.web.support.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xufeng
 *
 */
@Data
@Component
@ConfigurationProperties(prefix="system.config")
public class SystemConfigProperties {
	
	/** 系统固定Locale */
	private String fixedLocale;
	
	/** 根据参数确定当前请求的Locale */
	private String localeRequestParameterName;
	
}
