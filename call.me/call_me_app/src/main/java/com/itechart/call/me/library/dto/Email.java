package com.itechart.call.me.library.dto;

import com.itechart.call.me.library.entity.Contact;
import lombok.*;

import java.util.List;

/** This dto represents data from client
 * for sending emails
 *
 * @author Hohin Yury
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class Email {
    private List<Contact> recipients;
    private String theme;
    private String text;
}
