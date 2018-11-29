package com.itechart.call.me.library.repository.interf;

import com.itechart.call.me.library.dto.ContactFilter;
import com.itechart.call.me.library.entity.Contact;

import java.util.List;

public interface ContactRepositoryInterface {
    Contact addContact(Contact contact);
    Contact updateContact(Contact contact);
    Contact deleteContact(Long id);
    Contact getContact(Long id);
    List<Contact> getAllContacts(Integer pageNumber);
    List<Contact> getAllContactsWithTodayBirthdays();
    Long count();
    List<Contact> find(ContactFilter contactFilter);
}
