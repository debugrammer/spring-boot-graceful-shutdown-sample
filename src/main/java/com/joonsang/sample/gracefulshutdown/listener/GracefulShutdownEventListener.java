package com.joonsang.sample.gracefulshutdown.listener;

import com.joonsang.sample.gracefulshutdown.config.GracefulShutdownTomcatConnector;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Graceful Shutdown Event Listener
 * @author debugrammer
 * @version 1.0
 * @since 2019-11-17
 */
@Component
public class GracefulShutdownEventListener implements ApplicationListener<ContextClosedEvent> {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GracefulShutdownEventListener.class);

    private GracefulShutdownTomcatConnector gracefulShutdownTomcatConnector;

    public GracefulShutdownEventListener(GracefulShutdownTomcatConnector gracefulShutdownTomcatConnector) {
        this.gracefulShutdownTomcatConnector = gracefulShutdownTomcatConnector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        gracefulShutdownTomcatConnector.getConnector().pause();

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) gracefulShutdownTomcatConnector.getConnector()
            .getProtocolHandler()
            .getExecutor();

        threadPoolExecutor.shutdown();

        try {
            threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);

            log.info("Web Application Gracefully Stopped.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();

            log.error("Web Application Graceful Shutdown Failed.");
        }
    }
}
