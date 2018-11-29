package com.itechart.call.me.library.repository.implementation;

import com.itechart.call.me.library.connection.ConnectionPool;
import com.itechart.call.me.library.entity.Phone;
import com.itechart.call.me.library.repository.implementation.constants.PhoneRepositoryConstants;
import com.itechart.call.me.library.repository.interf.PhoneRepositoryInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Extension of PhoneRepositoryInterface
 * Used for making CRUD operations with the database
 *
 * Use:
 * PhoneRepositoryInterface repository =
 *      PhoneRepository.getInstance();
 *
 * @author Hohin Yury
 *
 */
public class PhoneRepository implements PhoneRepositoryInterface {

    private static Logger logger = LogManager.getLogger(PhoneRepository.class);

    private static PhoneRepository instance;

    /**
     * This method helps to transfer data from jdbc result
     * of executed statement to corresponding phone entity
     * @param phone - corresponding phone entity
     * @param result - result of executing
     */
    private void setPhoneDataFromResultSet(ResultSet result, Phone phone)
            throws SQLException {

        phone.setId(result.getLong(PhoneRepositoryConstants.ID_PARAM_NAME));
        phone.setComment(result.getString(PhoneRepositoryConstants.COMMENT_PARAM_NAME));
        phone.setCountryCode(result.getInt(PhoneRepositoryConstants.COUNTRY_CODE_PARAM_NAME));
        phone.setOperatorCode(result.getInt(PhoneRepositoryConstants.OPERATOR_CODE_PARAM_NAME));
        phone.setPhoneNumber(result.getInt(PhoneRepositoryConstants.PHONE_NUMBER_PARAM_NAME));
        phone.setContactId(result.getLong(PhoneRepositoryConstants.CONTACT_ID_PARAM_NAME));

    }

    /**
     * This method helps to transfer data from phone entity to
     * corresponding prepared statement
     * @param phone - phone entity
     * @param statement - corresponding prepared statement
     */
    private void setPreperedStatementDataFromPhone(PreparedStatement statement, Phone phone)
            throws SQLException {
        statement.setObject(1, phone.getComment());
        statement.setInt(2, phone.getCountryCode());
        statement.setInt(3, phone.getOperatorCode());
        statement.setInt(4, phone.getPhoneNumber());
        statement.setLong(5, phone.getContactId());
    }

    public PhoneRepository(){
    }

    /**
     * Implimentation of singleton pattern
     */
    public static PhoneRepository getInstance(){
        if (instance != null){
            return instance;
        }
        instance = new PhoneRepository();
        return instance;
    }

    private ConnectionPool connectionPool = new ConnectionPool();

    /**
     * Adds phone to db
     * @param phone - adding phone entity
     */
    @Override
    public Phone addPhone(Phone phone) throws SQLException {
        ResultSet generatedKeys;

        try(Connection connection = connectionPool.getConnection()){
            try(PreparedStatement statement =
                        connection.prepareStatement(PhoneRepositoryConstants.addPhoneSQL, Statement.RETURN_GENERATED_KEYS)) {

                setPreperedStatementDataFromPhone(statement, phone);
                statement.executeUpdate();
                generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    phone.setId(generatedKeys.getLong(1));
                }
                return phone;
            }
        }
    }

    /**
     * Updates phone in db
     * @param phone - updating phone entity
     */
    @Override
    public Phone updatePhone(Phone phone) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys;

        connection = connectionPool.getConnection();
        statement = connection.prepareStatement(PhoneRepositoryConstants.updatePhoneSQL, Statement.RETURN_GENERATED_KEYS);
        setPreperedStatementDataFromPhone(statement, phone);
        statement.setLong(6, phone.getId());

        statement.executeUpdate();
        generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            phone.setId(generatedKeys.getLong(1));
        }
        return phone;
    }

    /**
     * Finds all contact phones in db
     * @param contactId - id of contact entity
     */
    @Override
    public List<Phone> getAllContactPhones(Long contactId) throws SQLException {

        logger.info("Getting all contact phones " + contactId);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        final List<Phone> phones = new ArrayList<>();

        connection = connectionPool.getConnection();
        statement = connection.prepareStatement(PhoneRepositoryConstants.getAllContactPhonesSQL);
        statement.setLong(1, contactId);
        result = statement.executeQuery();
        Phone phone = new Phone();
        while (result.next()) {
            setPhoneDataFromResultSet(result, phone);
            phones.add(phone);
            phone = new Phone();
        }
        return phones;

    }

    /**
     * Sets delition flag to non null
     * @param id - id of phone entity
     */
    @Override
    public void deletePhone(Long id) throws SQLException, IOException {
        try {
            ResultSet result = null;

            logger.info("Deleting phone " + id);

            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(PhoneRepositoryConstants.deletePhoneSQL)) {
                    statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                    statement.setLong(2, id);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            logger.error("Can't phone contact with id " + id);
        }
    }

    /**
     * Finds phone in db
     * @param id - id of phone entity
     */
    @Override
    public Phone getPhone(Long id) throws SQLException {
        logger.info("Getting phone id db with id: " + id);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Phone phone = null;

        connection = connectionPool.getConnection();
        statement = connection.prepareStatement(PhoneRepositoryConstants.getPhoneSQL);
        statement.setLong(1, id);
        result = statement.executeQuery();

        while(result.next()) {
            phone = new Phone();
            setPhoneDataFromResultSet(result, phone);
        }

        return phone;
    }

}
