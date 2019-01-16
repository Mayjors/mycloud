package com.eu.manager.config.tomcat;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 设置Tomcat性能(springboot2.0)
 * @author yuanjie
 * @date 2019/1/16 16:58
 */
@Configuration
public class MyServletWebServerFactory {
    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(new MyTomcatServletWebCustommizer());
        return tomcat;
    }

    class MyTomcatServletWebCustommizer implements TomcatConnectorCustomizer {

        @Override
        public void customize(Connector connector) {
            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            // 设置最大连接数
            protocol.setMaxConnections(2000);
            // 设置最大线程数
            protocol.setMaxThreads(2000);
            protocol.setConnectionTimeout(30000);
        }
    }
}
