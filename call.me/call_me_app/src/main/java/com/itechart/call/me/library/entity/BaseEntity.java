package com.itechart.call.me.library.entity;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class represents BaseEntity entity
 *
 * @author Yuriy
 */

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class BaseEntity implements Serializable {
    protected Long id;
    private LocalDateTime delitionDate;
}
