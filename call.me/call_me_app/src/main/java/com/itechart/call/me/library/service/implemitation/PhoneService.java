package com.itechart.call.me.library.service.implemitation;

import com.itechart.call.me.library.entity.Phone;
import com.itechart.call.me.library.repository.implementation.PhoneRepository;
import com.itechart.call.me.library.repository.interf.PhoneRepositoryInterface;
import com.itechart.call.me.library.service.interf.PhoneServiceInterface;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PhoneService implements PhoneServiceInterface {

    private final PhoneRepositoryInterface phoneRepository = PhoneRepository.getInstance();

    @Override
    public List<Phone> getContactPhones(Long contactId) throws IOException, SQLException {
        return phoneRepository.getAllContactPhones(contactId);
    }

    @Override
    public Phone getPhoneById(Long id) throws SQLException {
        return phoneRepository.getPhone(id);
    }

    @Override
    public Phone addPhone(Phone phone) throws SQLException {
        return phoneRepository.addPhone(phone);
    }

    public Phone updatePhone(Phone phone) throws SQLException {
        return phoneRepository.updatePhone(phone);
    }

    @Override
    public void deletePhone(Long id) throws IOException, SQLException {
        phoneRepository.deletePhone(id);
    }
}
