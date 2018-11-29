package com.itechart.call.me.library.service.interf;

import com.itechart.call.me.library.entity.Phone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PhoneServiceInterface {
    List<Phone> getContactPhones(Long contactId) throws IOException, SQLException;
    Phone getPhoneById(Long id) throws SQLException;
    Phone addPhone(Phone phone) throws SQLException;
    void deletePhone(Long id) throws IOException, SQLException;
}
