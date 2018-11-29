package com.itechart.call.me;

import com.itechart.call.me.library.service.implemitation.ScheduledEmailSendingService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private Logger logger =  LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void startupSchedule() throws SchedulerException {
        logger.info("Startup schedule");
        ScheduledEmailSendingService.startScheduling();
    }

    @EventListener(ContextStoppedEvent.class)
    public void shutdouwnSchedule() throws SchedulerException {
        logger.info("Shutdown schedule");
        ScheduledEmailSendingService.shutScheduling();
    }
}
