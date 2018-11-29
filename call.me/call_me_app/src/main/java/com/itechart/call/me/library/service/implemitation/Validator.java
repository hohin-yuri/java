package com.itechart.call.me.library.service.implemitation;

import com.google.common.base.Strings;
import com.itechart.call.me.library.entity.Attachment;
import com.itechart.call.me.library.entity.Contact;
import com.itechart.call.me.library.entity.Phone;
import com.itechart.call.me.library.repository.implementation.ContactRepository;
import com.itechart.call.me.library.dto.ContactFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDateTime;

/** This class impliments validation actiovity
 * of the Contact entities
 *
 */
public class Validator {

    private ContactRepository contactRepository = ContactRepository.getInstance();

    private static final int DEFAULT_PAGE_SIZE = 50;
    private static final int LONG_TEXT_FIELD_LENGTH = 255;

    private Logger logger = LogManager.getLogger(Validator.class);

    public boolean validateContact(Contact contact) {
        logger.info("Validaiting contact " + contact);

        if(contact == null) {
            return false;
        }

        if (contact.getBirthday() != null && contact.getBirthday().isAfter(LocalDateTime.now())) {
            return false;
        }

        return !(
                (!Strings.isNullOrEmpty(contact.getFirstName())
                        && contact.getFirstName().length() > LONG_TEXT_FIELD_LENGTH) ||
                (!Strings.isNullOrEmpty(contact.getSecondName())
                        && contact.getSecondName().length() > LONG_TEXT_FIELD_LENGTH) ||
                contact.getEmail().matches("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b"));
    }


    public boolean validateFilter(ContactFilter contactFilter) {
        logger.info("Validaiting contact filter " + contactFilter);

        if(contactFilter == null) {
            return false;
        }

        LocalDateTime from = contactFilter.getBirthdayFrom();
        LocalDateTime to = contactFilter.getBirthdayTo();
        if (from != null && to != null && from.isAfter(to)) {
            return false;
        }

        return !(
                (!Strings.isNullOrEmpty(contactFilter.getFirstName())
                        && contactFilter.getFirstName().length() > LONG_TEXT_FIELD_LENGTH) ||
                (!Strings.isNullOrEmpty(contactFilter.getSecondName())
                        && contactFilter.getSecondName().length() > LONG_TEXT_FIELD_LENGTH));
    }


    public boolean validatePageNumber(Integer pageNumber) throws SQLException {
        logger.info("Validaiting number of page");

        return pageNumber <= Math.ceil(contactRepository.count() / DEFAULT_PAGE_SIZE);
    }

    public boolean validatePhone(Phone phone){
        logger.info("Validaiting phone " + phone);
        return !(phone == null ||
                !phone.getCountryCode().toString().matches("\\d{1,5}") ||
                !phone.getOperatorCode().toString().matches("\\d{1,5}") ||
                !phone.getPhoneNumber().toString().matches("\\d{1,11}") ||
                phone.getComment().length() > 256
        );
    }

    public boolean validateAttachment(Attachment attachment) throws SQLException {
        logger.info("Validaiting attachment " + attachment);
        return !(attachment == null ||
                attachment.getFile() == null ||
                attachment.getFile().length() / 1024.0 / 1024.0 > 1.0);
    }
}
