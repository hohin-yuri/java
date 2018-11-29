package com.itechart.call.me.library.service.implemitation;

import com.itechart.call.me.library.entity.Attachment;
import com.itechart.call.me.library.repository.implementation.AttachmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/** This class represents
 * service that works
 * with attachments
 *
 * @author Hohin Yury
 *
 */
public class FileUploadService {

    private final Logger logger =  LoggerFactory.getLogger(FileUploadService.class);

    private final AttachmentRepository attachmentRepository = AttachmentRepository.getInstance();

    /**
     * Generates attachment from controller data
     */
    public Attachment store(MultipartFile file, String comment, Long contactId) throws IOException, SQLException {
        Blob blob = new SerialBlob(file.getBytes());
        Attachment attachment = new Attachment();
        attachment.setComment(comment);
        attachment.setFilename(file.getOriginalFilename().replace(" ", "_"));
        attachment.setContactId(contactId);
        attachment.setFile(blob);
        attachment.setUploaded(LocalDateTime.now());
        logger.info("Storing attachment " + attachment);
        attachmentRepository.addAttachment(attachment);

        return attachment;
    }

    /**
     * Finds attachment in db
     */
    public Attachment load(Long attachmentId) throws SQLException {
        return attachmentRepository.getAttachment(attachmentId);
    }

    public List<Attachment> getAllContactAttachmentsWithoutBlob(Long contactId) throws SQLException {
        return attachmentRepository.getAllContactAttachmentsWithoutBlob(contactId);
    }

    public Attachment deleteAttachment(Long attachmentId) throws SQLException {
        return attachmentRepository.deleteAttachment(attachmentId);
    }

}
