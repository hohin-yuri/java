package com.itechart.call.me.library.repository.implementation;

import com.itechart.call.me.library.dto.ContactFilter;
import com.itechart.call.me.library.connection.ConnectionPool;
import com.itechart.call.me.library.entity.Contact;
import com.itechart.call.me.library.entity.enumirator.Gender;
import com.itechart.call.me.library.entity.enumirator.MaterialStatus;
import com.itechart.call.me.library.repository.implementation.constants.ContactRepositoryConstants;
import com.itechart.call.me.library.repository.interf.ContactRepositoryInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Extension of ContactRepositoryInterface
 * Used for making CRUD operations with the database
 *
 * Use:
 * ContactRepositoryInterface repository =
 *      ContactRepository.getInstance();
 *
 * @author Hohin Yury
 *
 */
public class ContactRepository implements ContactRepositoryInterface {

    private static Logger logger = LogManager.getLogger(ContactRepository.class);


    private static ContactRepository instance;

    /**
     * Implimentation of singleton pattern
     */
    public static ContactRepository getInstance(){
        logger.info("Getting contact repository instance");
        if (instance != null){
            return instance;
        }
        instance = new ContactRepository();
        return instance;
    }

    private ConnectionPool connectionPool = new ConnectionPool();


    /**
     * This method helps to transfer data from jdbc result
     * of executed statement to corresponding contact entity
     * @param contact - corresponding contact entity
     * @param result - result of executing
     */
    private void setContactDataFromResultSet(ResultSet result, Contact contact) throws SQLException {

        logger.info("Transformig data from db to Contact " + contact.toString());

        contact.setId(result.getLong(ContactRepositoryConstants.ID_PARAM_NAME));
        var birthday =  result.getTimestamp(ContactRepositoryConstants.BIRTHDAY_PARAM_NAME);
        if (birthday != null) contact.setBirthday(birthday.toLocalDateTime());
        contact.setCitizenship(result.getString(ContactRepositoryConstants.CITIZENSHIP_PARAM_NAME));
        contact.setFirstName(result.getString(ContactRepositoryConstants.FIRST_NAME_PARAM_NAME));
        contact.setSecondName(result.getString(ContactRepositoryConstants.SECOND_NAME_PARAM_NAME));
        contact.setSurname(result.getString(ContactRepositoryConstants.SURNAME_PARAM_NAME));
        contact.setGender(Gender.valueOf(result.getString(ContactRepositoryConstants.GENDER_PARAM_NAME)));
        contact.setJob(result.getString(ContactRepositoryConstants.JOB_PARAM_NAME));
        contact.setWebsite(result.getString(ContactRepositoryConstants.WEBSITE_PARAM_NAME));
        contact.setMaterialStatus(MaterialStatus.valueOf(result.getString(ContactRepositoryConstants.MATERIAL_STATUS_PARAM_NAME)));

        contact.setCity(result.getString(ContactRepositoryConstants.CITY));
        contact.setCountry(result.getString(ContactRepositoryConstants.COUNTRY));
        contact.setStreet(result.getString(ContactRepositoryConstants.STREET));
        contact.setApartment(result.getString(ContactRepositoryConstants.APARTMENT));
        contact.setZipCode(result.getInt(ContactRepositoryConstants.ZIP_CODE));
        contact.setEmail(result.getString(ContactRepositoryConstants.EMAIL));
    }

    /**
     * This method helps to transfer data from contact entity to
     * corresponding prepared statement
     * @param contact - contact entity
     * @param statement - corresponding prepared statement
     */
    private void setPreperedStatementDataFromContact(PreparedStatement statement, Contact contact)
            throws SQLException {

        logger.info("Transformig data from contact to Result Set " + contact.toString());

        if (contact.getBirthday() != null) {
            statement.setTimestamp(1, Timestamp.valueOf(contact.getBirthday()));
        }
        else {
            statement.setNull(1, Types.TIMESTAMP);
        }
        statement.setObject(2, contact.getCitizenship());
        statement.setObject(3, contact.getFirstName());
        statement.setObject(4, contact.getSecondName());
        statement.setObject(5, contact.getSurname());
        statement.setObject(6, contact.getGender().toString());
        statement.setObject(7, contact.getJob());
        statement.setObject(8, contact.getWebsite());
        statement.setObject(9, contact.getMaterialStatus().toString());
        statement.setObject(10, contact.getCountry());
        statement.setObject(11, contact.getStreet());
        statement.setObject(12, contact.getCity());
        statement.setObject(13, contact.getApartment());
        statement.setObject(14, contact.getZipCode());
        statement.setObject(15, contact.getEmail());

    }

    /**
     * This method helps to transfer data from contact filter entity to
     * corresponding prepared statement
     * @param contactFilter - contact filter entity
     * @param statement - corresponding prepared statement
     */
    private void setPreperedStatementDataFromContactFilter(PreparedStatement statement, ContactFilter contactFilter)
            throws SQLException {

        logger.info("Transformig data from contact to Result Set " + contactFilter.toString());

        if (contactFilter.getBirthdayFrom() != null) {
            statement.setTimestamp(1, Timestamp.valueOf(contactFilter.getBirthdayFrom()));
            statement.setTimestamp(2, Timestamp.valueOf(contactFilter.getBirthdayFrom()));
        }
        else {
            statement.setNull(1, Types.TIMESTAMP);
            statement.setNull(2, Types.TIMESTAMP);
        }
        if (contactFilter.getBirthdayFrom() != null) {
            statement.setTimestamp(3, Timestamp.valueOf(contactFilter.getBirthdayTo()));
            statement.setTimestamp(4, Timestamp.valueOf(contactFilter.getBirthdayTo()));
        }
        else {
            statement.setNull(3, Types.TIMESTAMP);
            statement.setNull(4, Types.TIMESTAMP);
        }
        statement.setObject(5, contactFilter.getCitizenship());
        statement.setObject(6, contactFilter.getCitizenship());

        statement.setObject(7, contactFilter.getFirstName());
        statement.setObject(8, contactFilter.getFirstName());

        statement.setObject(9, contactFilter.getSecondName());
        statement.setObject(10, contactFilter.getSecondName());

        statement.setObject(11, contactFilter.getSurname());
        statement.setObject(12, contactFilter.getSurname());

        statement.setObject(13, contactFilter.getGender().toString());
        statement.setObject(14, contactFilter.getGender().toString());

        statement.setObject(15, contactFilter.getMaterialStatus().toString());
        statement.setObject(16, contactFilter.getMaterialStatus().toString());

        statement.setObject(17, contactFilter.getCountry());
        statement.setObject(18, contactFilter.getCountry());

        statement.setObject(19, contactFilter.getStreet());
        statement.setObject(20, contactFilter.getStreet());

        statement.setObject(21, contactFilter.getCity());
        statement.setObject(22, contactFilter.getCity());

        statement.setObject(23, contactFilter.getApartment());
        statement.setObject(24, contactFilter.getApartment());

        statement.setObject(25, contactFilter.getZipCode());
        statement.setObject(26, contactFilter.getZipCode());
    }

    /**
     * Adds contact to db
     * @param contact - adding contact entity
     */
    @Override
    public Contact addContact(Contact contact) {
        try {
            ResultSet generatedKeys;
            logger.info("Adding contact to db " + contact.toString());
            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement =connection.prepareStatement(
                        ContactRepositoryConstants.ADD_CONTACT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                    setPreperedStatementDataFromContact(statement, contact);
                    statement.executeUpdate();
                    generatedKeys = statement.getGeneratedKeys();

                    if (generatedKeys.next()) {
                        contact.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Can't add contact! " + e);
            return null;
        }
        return contact;
    }

    /**
     * Updates contact data
     * @param contact - updating contact entity
     */
    @Override
    public Contact updateContact(Contact contact) {
        ResultSet generatedKeys;
        try {
            logger.info("Updating contact in db " + contact.toString());
            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement =connection.prepareStatement(
                        ContactRepositoryConstants.UPDATE_CONTACT_SQL, Statement.RETURN_GENERATED_KEYS)) {

                    setPreperedStatementDataFromContact(statement, contact);
                    statement.setLong(16, contact.getId());
                    statement.executeUpdate();
                    generatedKeys = statement.getGeneratedKeys();

                    if (generatedKeys.next()) {
                        contact.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Can't update contact!" + e);
            return null;
        }
        return contact;
    }


    /**
     * Gets contact with id
     * @param contactId - unical identificator of contact
     */
    @Override
    public Contact getContact(Long contactId) {
        ResultSet result = null;
        Contact contact = new Contact();
        try {
            logger.info("Getting contact " + contactId);

            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(
                        ContactRepositoryConstants.SELECT_FROM_CONTACT_WHERE_CONTACT_ID)) {
                    statement.setLong(1, contactId);
                    result = statement.executeQuery();
                    result.next();

                    setContactDataFromResultSet(result, contact);
                }
            }
        } catch (SQLException e) {
            logger.error("Can't get contact " + contactId);
            return null;
        }


        return contact;

    }

    /**
     * Sets delete status to contact
     * @param id - unical identificator of deleting contact
     */
    @Override
    public Contact deleteContact(Long id) {
        try {
            ResultSet result = null;

            logger.info("Deleting contact " + id);

            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(ContactRepositoryConstants.DELETE_CONTACT_SQL)) {
                    statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                    statement.setLong(2, id);
                    statement.executeUpdate();
                    return getContact(id);
                }
            }
        } catch (SQLException e) {
            logger.error("Can't delete contact with id " + id);
            return null;
        }

    }

    /**
     * Gets page of contacts in the database
     * @param pageNumber number of the getting page
     */
    @Override
    public List<Contact> getAllContacts(Integer pageNumber) {
        try {
            logger.info("Getting all contacts in page " + pageNumber);
            ResultSet result = null;
            final List<Contact> contacts = new ArrayList<>();

            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(
                        ContactRepositoryConstants.GET_ALL_CONTACTS_SQL)) {
                    statement.setInt(1, (pageNumber - 1) * ContactRepositoryConstants.CONTACTS_PER_PAGE);
                    result = statement.executeQuery();

                    Contact contact = new Contact();
                    while (result.next()) {
                        setContactDataFromResultSet(result, contact);
                        contacts.add(contact);
                        contact = new Contact();
                    }
                    return contacts;

                }
            }
        } catch (SQLException e) {
            logger.error("Cant all contacts in page number " + pageNumber);
            return null;
        }
    }

    /**
     * Gets contacts that today celebrates birthday
     */
    @Override
    public List<Contact> getAllContactsWithTodayBirthdays() {
        try {
            logger.info("getting All Contacts With Today Birthdays");
            ResultSet result = null;
            final List<Contact> contacts = new ArrayList<>();

            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(
                        ContactRepositoryConstants.GET_ALL_CONTACTS_WITH_TODAY_BIRTHDAYS_SQL)) {
                    result = statement.executeQuery();

                    Contact contact = new Contact();
                    while (result.next()) {
                        setContactDataFromResultSet(result, contact);
                        contacts.add(contact);
                        contact = new Contact();
                    }
                    return contacts;

                }
            }
        } catch (SQLException e){
            logger.error("Cant all contacts with today birthday!");
            return null;
        }
    }

    /**
     * Gets number of contacts in the database
     */
    @Override
    public  Long count() {
        try {
            logger.info("Getting a count of contacts in db");
            ResultSet result = null;
            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(ContactRepositoryConstants.COUNT_SQL)) {
                    result = statement.executeQuery();
                    result.next();
                    long count = result.getLong("rowcount");
                    return count;

                }
            }
        } catch (SQLException e){
            logger.error("Can't fetch contacts count!");
            return null;
        }
    }

    /**
     * Finds all contacts that match filtering params
     * @param contactFilter dto of filtering params
     */
    @Override
    public List<Contact> find(ContactFilter contactFilter) {
        try {
            logger.info("Finding a contact with params " + contactFilter.toString());
            ResultSet result = null;
            final List<Contact> contacts = new ArrayList<>();

            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(ContactRepositoryConstants.FIND_FILTRED_CONTACTS_SQL)) {
                    setPreperedStatementDataFromContactFilter(statement, contactFilter);
                    result = statement.executeQuery();
                    Contact contact = new Contact();

                    while (result.next()) {
                        setContactDataFromResultSet(result, contact);
                        contacts.add(contact);
                        contact = new Contact();
                    }
                    return contacts;

                }
            }
        } catch (SQLException e) {
            logger.error("Can't find contacts with this filter " + contactFilter.toString() + e);
            return null;
        }

    }

}
