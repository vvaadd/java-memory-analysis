package org.springframework.samples.petclinic.spring;

import ch.qos.logback.classic.LoggerContext;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;

public class Cleaner {
    @PreDestroy
    public void clean() throws InterruptedException {
        AbandonedConnectionCleanupThread.shutdown();
        ((LoggerContext) LoggerFactory.getILoggerFactory()).stop();
    }
}
