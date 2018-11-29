package com.itechart.call.me.library.service.implemitation;

import com.itechart.call.me.library.dto.Email;
import com.itechart.call.me.library.entity.Contact;
import com.itechart.call.me.library.util.EmailSender;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/** This class represents
 * service that
 * autogenerates and sends emails every midnight
 *
 * @author Hohin Yury
 *
 */
public final class ScheduledEmailSendingService implements Job {

    private final static String BIRTHDAY_WISH_TEXT = "Dear <name>, happy birthday!";
    private static String THEME = "Birthday!";
    private final ContactService contactService = new ContactService();
    private final static SchedulerFactory schedFact = new StdSchedulerFactory();
    private static Scheduler sched;

    static {
        try {
            sched = schedFact.getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /**
     * This code will executes
     * every timedelta, our job
     */
    @Override
    public void execute(JobExecutionContext context) {
        List<Contact> contacts = contactService.getAllContactsWithTodayBirthdays();
        Email email = new Email();
        email.setRecipients(contacts);
        email.setTheme(THEME);
        email.setText(BIRTHDAY_WISH_TEXT);
        EmailSender.sendEmail(email);
    }

    /**
     * Sets up scheduling settings
     */
    public static void startScheduling() throws SchedulerException {

        sched.start();

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(ScheduledEmailSendingService.class)
                .withIdentity("myJob", "group1")
                .build();

        GregorianCalendar calEnd = new GregorianCalendar();
        calEnd.setTime(new Date());
        calEnd.set(GregorianCalendar.DAY_OF_YEAR, calEnd.get(GregorianCalendar.DAY_OF_YEAR) + 1);
        calEnd.set(GregorianCalendar.HOUR_OF_DAY, 0);
        calEnd.set(GregorianCalendar.MINUTE, 0);
        calEnd.set(GregorianCalendar.SECOND, 0);
        calEnd.set(GregorianCalendar.MILLISECOND, 0);
        Date midnightTonight = calEnd.getTime();

        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startAt(midnightTonight)
                .withSchedule(simpleSchedule()
                        .withIntervalInHours(24)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
        sched.start();
    }

    /**
     * Terminates scheduling
     */
    public static void shutScheduling() throws SchedulerException {
        sched.shutdown();
    }
}
