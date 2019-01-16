package com.eu.manager.config.tomcat;

/**
 * 设置Tomcat性能(方式一)
 * TomcatEmbeddedServletContainerFactory --springboot1.5的类
 * @author yuanjie
 * @date 2019/1/16 16:00
 */
//@Component
public class MyEmbededServletContainerFactory {
//        extends TomcatEmbeddedServletContainerFactory {
    /*@Override
    public EmbeddedServletContainer getEmbeddedServletContainer(ServletContextInitializer... initializers) {
        this.setPort(9999);
        return super.getEmbeddedServletContainer(initializers);
    }

    @Override
    protected void customizeConnector(Connector connector) {
        super.customizeConnector(connector);
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        // 设置最大连接数
        protocol.setMaxConnections(2000);
        // 设置最大线程数
        protocol.setMaxThreads(2000);
        protocol.setConnectionTimeout(30000);
    }*/
}
