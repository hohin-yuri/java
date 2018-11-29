package com.itechart.call.me.controller;

import com.itechart.call.me.library.util.EmailSender;
import com.itechart.call.me.library.dto.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Class represents Email Controller
 * that handle requests connected
 * with emails
 *
 * @author Yuriy
 */
@RestController
@RequestMapping(value = "email/", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmailController {
    private final Logger logger =  LoggerFactory.getLogger(EmailController.class);
    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody Email email){
        //if (!validator.validateEmail(email)) {
          //  return ResponseEntity.unprocessableEntity().build();
        //}
        logger.info("Sending email " + email);
        EmailSender.sendEmail(email);

        return ResponseEntity.ok().build();
    }
}

