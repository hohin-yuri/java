package com.itechart.call.me.library.repository.implementation.constants;

/** This class contains constants
 * constants that used for crud operations
 * with Attachment entity in db
 *
 * @author Hohin Yury
 */
public final class AttachmentRepositoryConstants {
    public static final String SELECT_FROM_ATTACHMENT_WHERE_ATTACHMENT_ID = "SELECT * FROM ATTACHMENT WHERE ATTACHMENT.id = ?";
    public static final String GET_ALL_CONTACT_ATTACHMENTS_SQL = "SELECT id, contact_id, comment, uploaded, filename FROM ATTACHMENT " +
                                                                 "WHERE ATTACHMENT.contact_id = ? " +
                                                                 "AND ATTACHMENT.deletion_date IS NULL ";
    public static final String ADD_ATTACHMENT_SQL = "INSERT INTO ATTACHMENT " +
                                                    "(comment, file, filename, contact_id) " +
                                                    "VALUES (?, ?, ?, ?);";
    public static final String DELETE_ATTACHMENT_SQL = "UPDATE ATTACHMENT SET deletion_date = CURRENT_TIMESTAMP " +
                                                       "WHERE ATTACHMENT.id = ?; ";
    public static final String ID_PARAM_NAME = "id";
    public static final String COMMENT_PARAM_NAME = "comment";
    public static final String FILE_PARAM_NAME = "file";
    public static final String FILE_NAME_PARAM_NAME = "filename";
    public static final String CONTACT_ID_PARAM_NAME = "contact_id";
}
