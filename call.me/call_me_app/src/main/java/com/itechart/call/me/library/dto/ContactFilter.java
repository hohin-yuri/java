package com.itechart.call.me.library.dto;

import com.itechart.call.me.library.entity.enumirator.Gender;
import com.itechart.call.me.library.entity.enumirator.MaterialStatus;
import lombok.*;

import java.time.LocalDateTime;

/** This dto represents data from client
 * for searching contacts
 *
 * @author Hohin Yury
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ContactFilter {
    private String firstName;
    private String secondName;
    private String surname;
    private LocalDateTime birthdayFrom;
    private LocalDateTime birthdayTo;
    private Gender gender;
    private String citizenship;
    private MaterialStatus materialStatus;
    private String country;
    private String city;
    private String street;
    private String apartment;
    private Integer zipCode;
}
