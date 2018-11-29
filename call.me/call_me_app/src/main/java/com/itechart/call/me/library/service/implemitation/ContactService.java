package com.itechart.call.me.library.service.implemitation;

import com.itechart.call.me.library.dto.ContactFilter;
import com.itechart.call.me.library.entity.Contact;
import com.itechart.call.me.library.repository.implementation.ContactRepository;

import java.util.List;
/** This class represents
 * service that works
 * with contacts
 *
 * @author Hohin Yury
 *
 */
public class ContactService {

    private final ContactRepository contactRepository = ContactRepository.getInstance();

    public Contact addContact(Contact contact) {
        return contactRepository.addContact(contact);
    }

    public Contact deleteContact(Long contactId) {
        return contactRepository.deleteContact(contactId);
    }

    public Contact update(Contact contact) {
        return contactRepository.updateContact(contact);
    }

    public Contact getContactById(Long contactId) {
        return contactRepository.getContact(contactId);
    }

    public List<Contact> getAllContacts(Integer pageNumber) {
        return contactRepository.getAllContacts(pageNumber);
    }

    public Long count() {
        return contactRepository.count();
    }

    public List<Contact> findContacts(ContactFilter contactFilter) {
        return contactRepository.find(contactFilter);
    }

    public List<Contact> getAllContactsWithTodayBirthdays() {
        return contactRepository.getAllContactsWithTodayBirthdays();
    }
}
