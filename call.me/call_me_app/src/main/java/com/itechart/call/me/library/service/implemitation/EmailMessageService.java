package com.itechart.call.me.library.service.implemitation;

import com.itechart.call.me.library.util.EmailSender;
import com.itechart.call.me.library.dto.Email;

/** This class represents
 * service sends emails
 *
 * @author Hohin Yury
 *
 */
public class EmailMessageService {

    public static void sendEmail(Email email) {
        EmailSender.sendEmail(email);
    }

}
