package com.joonsang.sample.gracefulshutdown.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Tomcat Config
 * @author debugrammer
 * @version 1.0
 * @since 2019-11-17
 */
@Configuration
public class TomcatConfig {

    private final GracefulShutdownTomcatConnector gracefulShutdownTomcatConnector;

    public TomcatConfig(GracefulShutdownTomcatConnector gracefulShutdownTomcatConnector) {
        this.gracefulShutdownTomcatConnector = gracefulShutdownTomcatConnector;
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(gracefulShutdownTomcatConnector);

        return factory;
    }
}
