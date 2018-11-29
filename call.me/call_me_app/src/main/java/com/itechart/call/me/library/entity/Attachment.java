package com.itechart.call.me.library.entity;

import lombok.*;

import java.sql.Blob;
import java.time.LocalDateTime;

/**
 * Class represents Attachment entity
 *
 * @author Yuriy
 */

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
@Setter
public class Attachment extends BaseEntity {

    private Long id;
    private Blob file;
    private LocalDateTime uploaded;
    private String filename;
    private String comment;
    private Long contactId;

}
