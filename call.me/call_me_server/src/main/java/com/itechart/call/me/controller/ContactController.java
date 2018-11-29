package com.itechart.call.me.controller;

import com.itechart.call.me.library.dto.ContactFilter;
import com.itechart.call.me.library.entity.Contact;
import com.itechart.call.me.library.service.implemitation.AvatarService;
import com.itechart.call.me.library.service.implemitation.ContactService;
import com.itechart.call.me.library.service.implemitation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

/**
 * Class represents Contact Controller
 * that handle requests connected
 * with contacts
 *
 * @author Yuriy
 */
@RestController
@RequestMapping(value = "contacts", produces = "application/json; charset=UTF-8")
public class ContactController {

    private final Logger logger =  LoggerFactory.getLogger(ContactController.class);
    private final ContactService contactService = new ContactService();

    private Validator validator = new Validator();

    @GetMapping("/page={pageNumber}")
    public ResponseEntity<?> getAllContacts(@PathVariable("pageNumber") Integer pageNumber) throws SQLException {
        logger.info("Returning all contacts on page " + pageNumber);

        List<Contact> contacts = contactService.getAllContacts(pageNumber);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<?> getContact(@PathVariable("contactId") Long contactId) throws SQLException {
        logger.info("Returning contact with id " + contactId);

        Contact contact = contactService.getContactById(contactId);
        return ResponseEntity.ok(contact);
    }

    @PutMapping(produces="application/json;charset=UTF-8")
    public ResponseEntity<?> updateContact(@RequestBody Contact contact) {
        if (!validator.validateContact(contact)) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Contact updated = contactService.update(contact);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @PostMapping(produces="application/json;charset=UTF-8")
    public ResponseEntity<?> addContact(@RequestBody Contact contact) {
        logger.info("Adding contact " + contact.toString());
        if (!validator.validateContact(contact)) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Contact updated = contactService.addContact(contact);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<?> deleteContact(@PathVariable("contactId") Long contactId) {

        Contact contact = contactService.deleteContact(contactId);

        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contact);

    }

    @GetMapping("/count")
    public Long count() {
        return contactService.count();
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchContacts(@RequestBody ContactFilter contactFilter) {
        List<Contact> contacts = contactService.findContacts(contactFilter);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/avatar/{contactId}/**")
    public ResponseEntity<?> getAvatar(@PathVariable("contactId") Long contactId) {
        final String sRootPath = ContactController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return ResponseEntity.ok(AvatarService.getAvatar(sRootPath, contactId));
    }

    @PostMapping("/avatar/{contactId}")
    public ResponseEntity<?> getAvatar(@PathVariable("contactId") Long contactId,
                                       @RequestParam("file") MultipartFile avatar) {
        final String sRootPath = ContactController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return ResponseEntity.ok(AvatarService.saveAvatar(sRootPath, contactId, avatar));
    }
}
