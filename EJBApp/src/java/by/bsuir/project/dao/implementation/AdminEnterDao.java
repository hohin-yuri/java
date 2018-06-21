package by.bsuir.project.dao.implementation;

import by.bsuir.project.dao.interf.AbstractDao;
import by.bsuir.project.dao.interf.AdminEnterDaoInterface;
import by.bsuir.project.entity.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class AdminEnterDao extends AbstractDao implements AdminEnterDaoInterface{
    private final String checkSQL = "SELECT * FROM admin WHERE login = ? AND password = ?";
    private final String checkLoginErrorMessage = "CHECK_LOGIN_ADMIN_ENTER_DAO_FAIL";
    private final String resultCloseErrorMessage = "RESULT_CLOSE_CHECK_LOGIN_ADMIN_ENTER_DAO_FAIL";
    private final String statementCloseErrorMessage = "STATEMENT_CLOSE_CHECK_LOGIN_ADMIN_ENTER_DAO_FAIL";
    
    private static AdminEnterDao instance;
            
    private AdminEnterDao(){
    } 
    

    public static AdminEnterDao getInstance(){
        if (instance==null){
           instance = new AdminEnterDao();
        }
        return instance;
    }
    

    @Override
    public boolean checkLogin(String login, String password) throws DaoException{        
    Connection connection = null;
    PreparedStatement statement = null;                
    ResultSet result = null;
    
    try {                            
        connection = getConnection();                
        statement = connection.prepareStatement(checkSQL);                                
        statement.setString(1, login);
        statement.setString(2, password);                
        result = statement.executeQuery();                                                      
        return result.next();
    }
    catch (SQLException e) {
        throw new DaoException(checkLoginErrorMessage, e);    
    }
    finally{          
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                throw new DaoException(resultCloseErrorMessage, e);    
            }
        } else {
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DaoException(statementCloseErrorMessage, e);    
            }
        }
        releaseConnection(connection);
    }                  
                
        
    }
}
