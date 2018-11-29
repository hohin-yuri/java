package com.itechart.call.me.library.entity;

import com.itechart.call.me.library.entity.enumirator.PhoneType;
import lombok.*;

import java.io.Serializable;

/**
 * Class represents Phone entity
 *
 * @author Yuriy
 */

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
public class Phone extends BaseEntity implements Serializable {
    private Long id;
    private Integer countryCode;
    private Integer operatorCode;
    private Integer phoneNumber;
    private PhoneType phoneType;
    private String comment;
    private Long contactId;
}
