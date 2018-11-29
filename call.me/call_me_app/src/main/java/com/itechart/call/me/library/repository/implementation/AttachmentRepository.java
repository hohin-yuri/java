package com.itechart.call.me.library.repository.implementation;

import com.itechart.call.me.library.connection.ConnectionPool;
import com.itechart.call.me.library.entity.Attachment;
import com.itechart.call.me.library.repository.implementation.constants.AttachmentRepositoryConstants;
import com.itechart.call.me.library.repository.interf.AttachmentRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/** Extension of AttachmentRepositoryInterface
 * Used for making CRUD operations with the database
 *
 * Use:
 * AttachmentRepositoryInterface repository =
 *      AttachmentRepository.getInstance();
 *
 * @author Hohin Yury
 *
 */
public class AttachmentRepository implements AttachmentRepositoryInterface {

    private static AttachmentRepository instance;

    Logger logger =  LoggerFactory.getLogger(AttachmentRepository.class);

    /**
     * This method helps to transfer data from jdbc result
     * of executed statement to corresponding attachment entity
     * @param attachment - corresponding attachment entity
     * @param result - result of executing
     */
    private void setAttachmentDataFromResultSet(ResultSet result, Attachment attachment)
            throws SQLException {
        attachment.setId(result.getLong(AttachmentRepositoryConstants.ID_PARAM_NAME));
        attachment.setComment(result.getString(AttachmentRepositoryConstants.COMMENT_PARAM_NAME));
        attachment.setFilename(result.getString(AttachmentRepositoryConstants.FILE_NAME_PARAM_NAME));
        attachment.setContactId(result.getLong(AttachmentRepositoryConstants.CONTACT_ID_PARAM_NAME));
    }

    /** This method transfers blob data from
     * jdbc result to attachment entity
     */
    private void setAttachmentBlob(ResultSet result, Attachment attachment) throws SQLException {
        attachment.setFile(result.getBlob(AttachmentRepositoryConstants.FILE_PARAM_NAME));
    }

    /**
     * This method helps to transfer data from phone attachment to
     * corresponding prepared statement
     * @param attachment - attachment entity
     * @param statement - corresponding prepared statement
     */
    private void setPreperedStatementDataFromAttachment(PreparedStatement statement, Attachment attachment)
            throws SQLException {
        statement.setString(1, attachment.getComment());
        statement.setBlob(2, attachment.getFile());
        statement.setString(3, attachment.getFilename());
        statement.setLong(4, attachment.getContactId());
    }

    private AttachmentRepository(){
    }

    /**
     * Implimentation of singleton pattern
     */
    public static AttachmentRepository getInstance(){
        if (instance != null){
            return instance;
        }
        instance = new AttachmentRepository();
        return instance;
    }

    private ConnectionPool connectionPool = new ConnectionPool();

    /**
     * Adds attachment to db
     * @param attachment - adding phone entity
     */
    @Override
    public Attachment addAttachment(Attachment attachment) {
        ResultSet generatedKeys;
        try {
            try(Connection connection = connectionPool.getConnection()) {
                try(PreparedStatement statement =
                        connection.prepareStatement(AttachmentRepositoryConstants.ADD_ATTACHMENT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                    setPreperedStatementDataFromAttachment(statement, attachment);

                    statement.executeUpdate();
                    generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        attachment.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Cant add attachment " + e);
            return null;
        }

        return attachment;
    }

    /**
     * Finds all contact attachments in db without its blob data
     * @param contactId - id of contact entity
     */
    @Override
    public List<Attachment> getAllContactAttachmentsWithoutBlob(Long contactId) throws SQLException {
        ResultSet result = null;
        List<Attachment> attachments = new ArrayList<>();
        try {
            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(
                        AttachmentRepositoryConstants.GET_ALL_CONTACT_ATTACHMENTS_SQL)) {
                    statement.setLong(1, contactId);
                    result = statement.executeQuery();
                    Attachment attachment = new Attachment();
                    while (result.next()) {
                        setAttachmentDataFromResultSet(result, attachment);
                        attachments.add(attachment);
                        attachment = new Attachment();
                    }
                    return attachments;
                }
            }
        } catch (SQLException e){
            logger.error(e.toString());
            return null;
        }
    }

    /**
     * Sets delition flag to non null
     * @param id - id of attachment entity
     */
    @Override
    public Attachment deleteAttachment(Long id) {
        try {
            try(Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(AttachmentRepositoryConstants.DELETE_ATTACHMENT_SQL)) {
                    logger.info("Deleting attachment " + id);
                    statement.setLong(1, id);
                    statement.executeUpdate();

                    return getAttachment(id);
                }
            }
        } catch (SQLException e){
            logger.error(e.toString());
            return null;
        }
    }

    /**
     * Finds attachment in db
     * @param id - id of phone entity
     */
    @Override
    public Attachment getAttachment(Long id) {
        ResultSet result = null;
        Attachment attachment = new Attachment();
        try {
            try (Connection connection = connectionPool.getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(
                        AttachmentRepositoryConstants.SELECT_FROM_ATTACHMENT_WHERE_ATTACHMENT_ID)) {

                    statement.setLong(1, id);
                    result = statement.executeQuery();
                    result.next();

                    setAttachmentDataFromResultSet(result, attachment);
                    setAttachmentBlob(result, attachment);

                    return attachment;
                }
            }
        } catch (SQLException e) {
            logger.error("Cant add attachment " + e.toString());
            return null;
        }
    }
}

