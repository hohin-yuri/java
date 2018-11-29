package com.itechart.call.me.library.entity;

import com.itechart.call.me.library.entity.enumirator.Gender;
import com.itechart.call.me.library.entity.enumirator.MaterialStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class represents Contact entity
 *
 * @author Yuriy
 */

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter @Setter
public class Contact extends BaseEntity implements Serializable {

    private Long id;
    private String firstName;
    private String secondName;
    private String surname;
    private LocalDateTime birthday;
    private Gender gender;
    private String citizenship;
    private MaterialStatus materialStatus;
    private String email;
    private String website;
    private String job;
    private String country;
    private String city;
    private String street;
    private String apartment;
    private Integer zipCode;

}
