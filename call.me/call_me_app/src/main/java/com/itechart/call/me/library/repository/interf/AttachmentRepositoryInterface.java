package com.itechart.call.me.library.repository.interf;

import com.itechart.call.me.library.entity.Attachment;

import java.sql.SQLException;
import java.util.List;

public interface AttachmentRepositoryInterface {
    Attachment addAttachment(Attachment attachment) throws SQLException;
    Attachment deleteAttachment(Long id) throws SQLException;
    Attachment getAttachment(Long id) throws SQLException;
    List<Attachment> getAllContactAttachmentsWithoutBlob(Long contactId) throws SQLException;
}
