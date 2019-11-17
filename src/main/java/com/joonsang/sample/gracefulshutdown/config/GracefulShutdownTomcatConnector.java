package com.joonsang.sample.gracefulshutdown.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.stereotype.Component;

/**
 * Graceful Shutdown Tomcat Connector
 * @author debugrammer
 * @version 1.0
 * @since 2019-11-17
 */
@Component
public class GracefulShutdownTomcatConnector implements TomcatConnectorCustomizer {

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    public Connector getConnector() {
        return connector;
    }
}
