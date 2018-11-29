package com.itechart.call.me.library.repository.implementation.constants;

/** This class contains constants
 * constants that used for crud operations
 * with Phone entity in db
 *
 * @author Hohin Yury
 */
public final class PhoneRepositoryConstants {
    public final static String getPhoneSQL = "SELECT * FROM PHONE WHERE PHONE.id = ?";
    public final static String getAllContactPhonesSQL = "SELECT * FROM PHONE " +
                                                  "WHERE PHONE.contact_id = ? " +
                                                  "AND PHONE.deletion_date IS NULL;";
    public final static String addPhoneSQL = "INSERT INTO PHONE " +
            "(comment, countyCode, operatorCode, phoneNumber, contact_id)" +
            "VALUES (?, ?, ?, ?, ?)";
    public final static String deletePhoneSQL = "UPDATE PHONE SET deletion_date = ? " +
            "WHERE PHONE.id = ?;";
    public final static String updatePhoneSQL = "UPDATE PHONE SET comment = ?, countyCode = ?, " +
            "operatorCode = ?, phoneNumber = ?, contact_id = ? " +
            "WHERE PHONE.id = ?;";
    public final static String ID_PARAM_NAME = "id";
    public final static String COMMENT_PARAM_NAME = "comment";
    public final static String CONTACT_ID_PARAM_NAME = "contact_id";
    public static final String COUNTRY_CODE_PARAM_NAME = "countyCode";
    public static final String OPERATOR_CODE_PARAM_NAME = "operatorCode";
    public static final String PHONE_NUMBER_PARAM_NAME = "phoneNumber";
}
