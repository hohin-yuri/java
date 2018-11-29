package com.itechart.call.me.controller;

import com.itechart.call.me.library.entity.Attachment;
import com.itechart.call.me.library.service.implemitation.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.List;


/**
 * Class represents Attachment Controller
 * that handle requests connected
 * with attachments
 *
 * @author Yuriy
 */
@RestController
@RequestMapping(value = "attachment", produces="application/json;charset=UTF-8")
public class AttachmentController {

    private final FileUploadService fileUploadService = new FileUploadService();
    private final Logger logger =  LoggerFactory.getLogger(ContactController.class);


    @PostMapping(value = "/{contactId}", produces="application/json;charset=UTF-8")
    public ResponseEntity<?>  upload(@RequestParam("comment") String comment,
                                     @PathVariable("contactId") Long contactId,
                                     @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                Attachment attachment = fileUploadService.store(file, comment, contactId);

            } catch (Exception e) {
                e.printStackTrace();
                ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();


        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<?> deleteAttachment(@PathVariable("attachmentId") Long attachmentId) throws SQLException {

            Attachment attachment = fileUploadService.deleteAttachment(attachmentId);
            if (attachment == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().build();
    }

    @GetMapping("/contact={contactId}")
    public ResponseEntity<?> getContactAttachments(@PathVariable("contactId") Long contactId)
            throws IOException, SQLException {
        List<Attachment> attachments = fileUploadService.getAllContactAttachmentsWithoutBlob(contactId);
        return ResponseEntity.ok(attachments);
    }

    @GetMapping(value = "/{attachmentId}")
    public void getAttachment(@PathVariable("attachmentId") Long attachmentId,
                              HttpServletResponse response)
            throws SQLException, IOException {
        Attachment attachment = fileUploadService.load(attachmentId);
        logger.info("Sending file with name " + attachment.getFilename());

        String mimeType= URLConnection.guessContentTypeFromName(attachment.getFilename());
        if(mimeType==null){
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition",
                String.format("attachment; filename=\"" + attachment.getFilename() +"\""));


        response.setContentLength((int)attachment.getFile().length());

        InputStream inputStream = new BufferedInputStream(attachment.getFile().getBinaryStream());

        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }



    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
