package com.itechart.call.me.controller;

import com.itechart.call.me.library.entity.Phone;
import com.itechart.call.me.library.service.implemitation.PhoneService;
import com.itechart.call.me.library.service.implemitation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "phones", produces = MediaType.APPLICATION_JSON_VALUE)
public class PhoneController {

    PhoneService phoneService = new PhoneService();
    Validator validator = new Validator();

    Logger logger =  LoggerFactory.getLogger(ContactController.class);

    @PostMapping()
    public ResponseEntity<?> addPhoneToContact(@RequestBody Phone phone) throws SQLException {
       return ResponseEntity.ok(phoneService.addPhone(phone));
    }

    @DeleteMapping("/{phoneId}")
    public ResponseEntity<?> deletePhone(@PathVariable("phoneId") Long phoneId) throws IOException, SQLException {
        logger.info("Deliting phone " + phoneId);
        phoneService.deletePhone(phoneId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity<?> updatePhone(@RequestBody Phone phone) throws SQLException {
        Phone updated = phoneService.updatePhone(phone);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/contact={contactId}")
    public ResponseEntity<?> getContactPhones(@PathVariable("contactId") Long contactId) throws SQLException, IOException {
        List<Phone> phones = phoneService.getContactPhones(contactId);
        return ResponseEntity.ok(phones);
    }

    @GetMapping("/{phoneId}")
    public ResponseEntity<?> getPhone(@PathVariable("phoneId") Long phoneId) throws SQLException, IOException {
        logger.info("Getting phone " + phoneId);
        Phone phone = phoneService.getPhoneById(phoneId);
        if (phone == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(phone);
    }

}
