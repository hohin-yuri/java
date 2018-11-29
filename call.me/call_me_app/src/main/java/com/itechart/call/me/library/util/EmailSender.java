package com.itechart.call.me.library.util;

import com.itechart.call.me.library.Resource;
import com.itechart.call.me.library.dto.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.ST;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class EmailSender {

    private static final Resource resource = Resource.getInstance();
    private static final String from = resource.getProperty("FROM_EMAIL");
    private static final String password = resource.getProperty("EMAIL_SERVICE_PASSWORD");
    public static void sendEmail(Email email) {
        for(var i : email.getRecipients()){
            String text = email.getText();
            ST templ = new ST(text);
            templ.add("name", i.getFirstName() + " " + i.getSecondName());
            templ.add("address", i.getStreet() + " " + i.getApartment());
            sendEmailToRecipient(from, password, i.getEmail(), email.getTheme(), templ.render());
        }
    }

    private static void sendEmailToRecipient(String from, String password, String to, String theme, String text) {

        Logger logger =  LoggerFactory.getLogger(EmailSender.class);

        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });


        try{
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
            msg.setSubject(theme);
            msg.setSentDate(new Date());
            msg.setText(text);
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            System.out.println("Mail has been sent successfully");

        } catch (MessagingException mex) {
            mex.printStackTrace();
            logger.error("Can't send email");
        }
    }
}
