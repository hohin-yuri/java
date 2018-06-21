package by.bsuir.project.dao.implementation;

import by.bsuir.project.dao.interf.AbstractDao;
import by.bsuir.project.dao.interf.UserEnterDaoInterface;
import by.bsuir.project.entity.Customer;
import by.bsuir.project.entity.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserEnterDao extends AbstractDao implements UserEnterDaoInterface {
    private final String checkSQL = "SELECT * FROM customer WHERE login=? AND password=?";
    private final String checkLoginSQL = "SELECT * FROM customer WHERE login=?";
    private final String addUserSQL = "INSERT INTO customer(first_name, second_name, login, password, phone, address_id) values(?,?,?,?,?,?)";        
    private final String addAddressSQL = "INSERT INTO address(street, house, apartment) values(?,?,?)";        
    private final String checkLoginErrorMessage = "CHECK_LOGIN_USER_ENTER_DAO_FAIL";
    private final String addUserErrorMessage = "ADD_USER_ENTER_DAO_FAIL";
    private final String PARAM_NAME_ID = "id";    
    private final String PARAM_NAME_LOGIN = "login";
    private final String PARAM_NAME_FIRST_NAME = "first_name";
    private final String PARAM_NAME_SECOND_NAME = "second_name";
    private static UserEnterDao instance;
            
    private UserEnterDao(){
    } 
    

    public static UserEnterDao getInstance(){
        if (instance==null){
           instance = new UserEnterDao();
        }
        return instance;
    }
    

    @Override
    public Customer checkCredentials(String login, String password) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        Customer customer;

        try {
            try {
                connection = getConnection();
                statement = connection.prepareStatement(checkSQL);
                statement.setString(1, login);
                statement.setString(2, password);                 
                result = statement.executeQuery();

                if (result.next()) {
                    customer = new Customer();
                    customer.setId(result.getInt(PARAM_NAME_ID));
                    customer.setLogin(result.getString(PARAM_NAME_LOGIN));
                    customer.setFirstName(result.getString(PARAM_NAME_FIRST_NAME));
                    customer.setSecondName(result.getString(PARAM_NAME_SECOND_NAME));
                    return customer;
                } else {
                    return null;
                }            

            } finally {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                releaseConnection(connection);
            }

        } catch (SQLException e) {
            throw new DaoException(checkLoginErrorMessage, e);
        }
    }
    @Override
    public int addCustomer(Customer customer) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;        
        int insertedId = 0;
        
        try {
            try {
                connection = getConnection();
                statement = connection.prepareStatement(addAddressSQL, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, customer.getAddress().getStreet());
                statement.setString(2, customer.getAddress().getHouse());                                 
                statement.setString(3, customer.getAddress().getApartment());
                statement.executeUpdate();                       
                generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()){
                    insertedId = generatedKeys.getInt(1);
                }
            } finally {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (statement != null) {
                    statement.close();
                }               
            }
            
            try {                
                statement = connection.prepareStatement(addUserSQL, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getSecondName());                                 
                statement.setString(3, customer.getLogin());
                statement.setString(4, customer.getPassword());
                statement.setString(5, customer.getPhone());
                statement.setInt(6, insertedId);
                insertedId = 0;
                statement.executeUpdate();                       
                generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()){
                    insertedId = generatedKeys.getInt(1);
                }
                
            } finally {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (statement != null) {
                    statement.close();
                }
                releaseConnection(connection);
            }

        } catch (SQLException e) {
            throw new DaoException(addUserErrorMessage, e);
        }
        return insertedId;
    }        
    @Override
    public boolean checkLogin(String login) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;        

        try {
            try {
                connection = getConnection();
                statement = connection.prepareStatement(checkLoginSQL);
                statement.setString(1, login);                
                result = statement.executeQuery();

                return result.next();            

            } finally {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                releaseConnection(connection);
            }

        } catch (SQLException e) {
            throw new DaoException(checkLoginErrorMessage, e);
        }
    }
}
