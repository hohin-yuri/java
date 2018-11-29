package com.itechart.call.me.library.repository.implementation.constants;

/** This class contains constants
 * constants that used for crud operations
 * with Contact entity in db
 *
 * @author Hohin Yury
 */
public final class ContactRepositoryConstants {

    public static final Integer CONTACTS_PER_PAGE = 20;
    public static final String ID_PARAM_NAME = "id";
    public static final String BIRTHDAY_PARAM_NAME = "birthday";
    public static final String CITIZENSHIP_PARAM_NAME = "citizenship";
    public static final String FIRST_NAME_PARAM_NAME = "firstName";
    public static final String SECOND_NAME_PARAM_NAME = "secondName";
    public static final String SURNAME_PARAM_NAME = "surname";
    public static final String GENDER_PARAM_NAME = "gender";
    public static final String JOB_PARAM_NAME = "job";
    public static final String WEBSITE_PARAM_NAME = "website";
    public static final String MATERIAL_STATUS_PARAM_NAME = "materialStatus";
    public static final String COUNTRY = "country";
    public static final String STREET = "street";
    public static final String CITY = "city";
    public static final String APARTMENT = "apartment";
    public static final String ZIP_CODE = "zipCode";
    public static final String EMAIL = "email";

    public final static String GET_ALL_CONTACTS_SQL = "SELECT * FROM CONTACT WHERE " +
                                                   "CONTACT.deletion_date IS NULL " +
                                                   "LIMIT ?" +
                                                   ", " + CONTACTS_PER_PAGE + ";";
    public final static String ADD_CONTACT_SQL = "INSERT INTO CONTACT " +
                                               "(birthday, citizenship, firstName, " +
                                               "secondName, surname, gender, " +
                                               "job, website, materialStatus," +
                                               "country, street, city, " +
                                                "apartment, zipCode, email) " +
                                              "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    public final static String UPDATE_CONTACT_SQL = "UPDATE CONTACT SET birthday = ?, citizenship = ?, firstName = ?, " +
                                                  "secondName = ?, surname = ?, gender = ?, " +
                                                  "job = ?, website = ?, materialStatus = ?," +
                                                  "country = ?, street = ?, city = ?, " +
                                                  "apartment = ?, zipCode = ?, email = ? " +
                                                  "WHERE CONTACT.id = ?;";
    public final static String DELETE_CONTACT_SQL = "UPDATE CONTACT SET deletion_date = ?" +
                                                  "WHERE CONTACT.id = ?;";
    public final static String SELECT_FROM_CONTACT_WHERE_CONTACT_ID = "SELECT * FROM CONTACT WHERE CONTACT.id = ?";
    public final static String COUNT_SQL = "SELECT COUNT(id) AS rowcount FROM CONTACT WHERE " +
                                          "CONTACT.deletion_date IS NULL";
    public final static String GET_ALL_CONTACTS_WITH_TODAY_BIRTHDAYS_SQL = "SELECT * FROM CONTACT " +
                                             "WHERE CONTACT.birthday = CURRENT_DATE;";
    public final static String FIND_FILTRED_CONTACTS_SQL = "SELECT * FROM CONTACT WHERE " +
                                                        "(? is NULL or birthday > ?) and " +
                                                        "(? is NULL or birthday < ?) and" +
                                                        "(? = '' or lower(citizenship) like CONCAT('%', lower(?), '%')) and " +
                                                        "(? = '' or lower(firstName) like CONCAT('%', lower(?), '%')) and " +
                                                        "(? = '' or lower(secondName) like CONCAT('%', lower(?), '%')) and " +
                                                        "(? = '' or lower(surname) like CONCAT('%', lower(?), '%')) and " +
                                                        "(? = '' or lower(gender) = lower(?)) and " +
                                                        "(? = '' or lower(materialStatus) = lower(?)) and" +
                                                        "(? = '' or lower(country) like CONCAT('%', lower(?), '%')) and " +
                                                        "(? = '' or lower(street) like CONCAT('%', lower(?), '%')) and " +
                                                        "(? = '' or lower(city) like CONCAT('%', lower(?), '%')) and " +
                                                        "(? = '' or lower(apartment) like CONCAT('%', lower(?), '%')) and " +
                                                        "(? is NULL or zipCode = ?);";
}
