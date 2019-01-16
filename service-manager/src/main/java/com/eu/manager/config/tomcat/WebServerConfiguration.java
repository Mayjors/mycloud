package com.eu.manager.config.tomcat;

/**
 * 设置Tomcat性能(方式二)
 * EmbeddedServletContainerFactory -- springboot1.5所拥有类
 * @author yuanjie
 * @date 2019/1/16 16:05
 */
//@Configuration
public class WebServerConfiguration {
//    @Bean
   /* public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory() {
        TomcatEmbeddedServletContainerFactory tomcatFactory  = new TomcatEmbeddedServletContainerFactory();
        tomcatFactory.setPort(9999);
        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
        return tomcatFactory;
    }

    class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
        @Override
        public void customize(Connector connector) {
            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            // 设置最大连接数
            protocol.setMaxConnections(2000);
            // 设置最大线程数
            protocol.setMaxThreads(2000);
            protocol.setConnectionTimeout(30000);
        }
    }*/
}
