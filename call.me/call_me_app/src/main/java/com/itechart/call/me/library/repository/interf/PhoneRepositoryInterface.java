package com.itechart.call.me.library.repository.interf;

import com.itechart.call.me.library.entity.Phone;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PhoneRepositoryInterface {
    public Phone addPhone(Phone phone) throws SQLException;
    public Phone updatePhone(Phone phone) throws SQLException;
    public void deletePhone(Long id) throws SQLException, IOException;
    public Phone getPhone(Long id) throws SQLException;
    public List<Phone> getAllContactPhones(Long id) throws  SQLException, IOException;
}
