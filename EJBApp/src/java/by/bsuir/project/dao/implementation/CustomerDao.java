package by.bsuir.project.dao.implementation;

import by.bsuir.project.dao.interf.AbstractDao;
import by.bsuir.project.dao.interf.CustomerDaoInterface;
import by.bsuir.project.entity.Address;
import by.bsuir.project.entity.Customer;
import by.bsuir.project.entity.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao class is used for operations with customers
 *
 * @author Albina
 */
public class CustomerDao extends AbstractDao implements CustomerDaoInterface {

    private final String getAllCustomersSQL = "SELECT * FROM 4elements.customer";
    private final String getBannedCustomersSQL = "SELECT * FROM 4elements.customer WHERE customer.banned = 1";
    private final String getNotBannedCustomersSQL = "SELECT * FROM 4elements.customer WHERE customer.banned = 0 or customer.banned IS NULL";
    private final String banCustomerSQL = "UPDATE 4elements.customer SET banned = 1 WHERE customer.id = ?";
    private final String unbanCustomerSQL = "UPDATE 4elements.customer SET banned = 0 WHERE customer.id = ?";
    private final String isBannedSQL = "SELECT * FROM 4elements.customer WHERE customer.id = ?";
    private final String getAddressSQL = "SELECT * FROM 4elements.address WHERE id = ?";
    private final String PARAM_NAME_ID = "id";
    private final String PARAM_NAME_LOGIN = "login";
    private final String PARAM_NAME_PASSWORD = "password";
    private final String PARAM_NAME_FIRST_NAME = "first_name";
    private final String PARAM_NAME_SECOND_NAME = "second_name";
    private final String PARAM_NAME_PHONE = "phone";
    private final String PARAM_NAME_STREET = "street";
    private final String PARAM_NAME_HOUSE = "house";
    private final String PARAM_NAME_APARTMENT = "apartment";
    private final String PARAM_NAME_BANNED = "banned";
    private final String isBannedCheckErrorMessage = "IS_BANNED_CHECK_CUSTOMER_DAO_FAIL";
    private final String getAllCustomersErrorMessage = "GET_ALL_CUSTOMERS_CUSTOMER_DAO_FAIL";
    private final String getBannedCustomersErrorMessage = "GET_BANNED_CUSTOMERS_CUSTOMER_DAO_FAIL";
    private final String banCustomerErrorMessage = "BAN_CUSTOMER_CUSTOMER_DAO_FAIL";
    private final String getNotBannedCustomersErrorMessage = "GET_NOT_BANNED_CUSTOMERS_CUSTOMER_DAO_FAIL";
    private final String unbanCustomerErrorMessage = "UNBAN_CUSTOMER_CUSTOMER_DAO_FAIL";
    private final String resultCloseErrorMessage = "RESULT_CLOSE_GET_ALL_CUSTOMERS_CUSTOMER_DAO_FAIL";
    private final String statementCloseErrorMessage = "STATEMENT_CLOSE_GET_ALL_CUSTOMERS_CUSTOMER_DAO_FAIL";
    private static CustomerDao instance;

    private CustomerDao() {
    }

    /**
     *
     * @return CustomerDao object
     */
    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }

    /**
     *
     * @return all customers from database
     * @throws by.bsuir.project.entity.exception.DaoException
     */
    @Override
    public List<Customer> getAllCustomers() throws DaoException {
        List<Customer> customers = new ArrayList<>();
        Connection connection1 = null, connection2 = null;
        PreparedStatement statement1 = null, statement2 = null;
        ResultSet result1 = null, result2 = null;

        try {
            connection1 = getConnection();
            statement1 = connection1.prepareStatement(getAllCustomersSQL);
            result1 = statement1.executeQuery();
            while (result1.next()) {
                Customer customer = new Customer();
                customer.setId(result1.getInt(PARAM_NAME_ID));
                customer.setLogin(result1.getString(PARAM_NAME_LOGIN));
                customer.setPassword(result1.getString(PARAM_NAME_PASSWORD));
                customer.setFirstName(result1.getString(PARAM_NAME_FIRST_NAME));
                customer.setSecondName(result1.getString(PARAM_NAME_SECOND_NAME));
                customer.setPhone(result1.getString(PARAM_NAME_PHONE));
                customer.setBanned((short) result1.getInt(PARAM_NAME_BANNED));

                try {
                    connection2 = getConnection();
                    statement2 = connection2.prepareStatement(getAddressSQL);
                    statement2.setInt(1, customer.getId());
                    result2 = statement2.executeQuery();
                    while (result2.next()) {
                        Address address = new Address();
                        address.setStreet(result2.getString(PARAM_NAME_STREET));
                        address.setHouse(result2.getString(PARAM_NAME_HOUSE));
                        address.setApartment(result2.getString(PARAM_NAME_APARTMENT));
                        customer.setAddress(address);                        
                    }                                
                    customers.add(customer);
                } catch (SQLException e) {                    
                    throw new DaoException(getAllCustomersErrorMessage, e);
                } finally {
                    if (result2 != null) {
                        try {
                            result2.close();
                        } catch (SQLException e) {
                            throw new DaoException(resultCloseErrorMessage, e);
                        }
                    }
                    if (statement2 != null) {
                        try {
                            statement2.close();
                        } catch (SQLException e) {
                            throw new DaoException(statementCloseErrorMessage, e);
                        }
                    }
                    releaseConnection(connection2);
                }                
            }

            return customers;
        } catch (SQLException e) {            
            throw new DaoException(getAllCustomersErrorMessage, e);
        } finally {
            if (result1 != null) {
                try {
                    result1.close();
                } catch (SQLException e) {
                    throw new DaoException(resultCloseErrorMessage, e);
                }
            }
            if (statement1 != null) {
                try {
                    statement1.close();
                } catch (SQLException e) {
                    throw new DaoException(statementCloseErrorMessage, e);
                }
            }
            releaseConnection(connection1);
        }

    }

    /**
     *
     * @return banned customers from database
     * @throws by.bsuir.project.entity.exception.DaoException
     */
    public List<Customer> getBannedCustomers() throws DaoException {
        List<Customer> customers = new ArrayList<>();
        Connection connection1 = null, connection2 = null;
        PreparedStatement statement1 = null, statement2 = null;
        ResultSet result1 = null, result2 = null;

        try {
            connection1 = getConnection();
            statement1 = connection1.prepareStatement(getBannedCustomersSQL);
            result1 = statement1.executeQuery();
            
                
            while (result1.next()) {
                Customer customer = new Customer();
                customer.setId(result1.getInt(PARAM_NAME_ID));
                customer.setLogin(result1.getString(PARAM_NAME_LOGIN));
                customer.setPassword(result1.getString(PARAM_NAME_PASSWORD));
                customer.setFirstName(result1.getString(PARAM_NAME_FIRST_NAME));
                customer.setSecondName(result1.getString(PARAM_NAME_SECOND_NAME));
                customer.setPhone(result1.getString(PARAM_NAME_PHONE));
                customer.setBanned((short) result1.getInt(PARAM_NAME_BANNED));
                
               
                try {
                    connection2 = getConnection();
                    statement2 = connection2.prepareStatement(getAddressSQL);
                    statement2.setInt(1, customer.getId());
                    result2 = statement2.executeQuery();
                    while (result2.next()) {
                        Address address = new Address();
                        address.setStreet(result2.getString(PARAM_NAME_STREET));
                        address.setHouse(result2.getString(PARAM_NAME_HOUSE));
                        address.setApartment(result2.getString(PARAM_NAME_APARTMENT));
                        customer.setAddress(address);                        
                    }            
                    customers.add(customer);
                } catch (SQLException e) {                    
                    throw new DaoException(getBannedCustomersErrorMessage, e);
                } finally {
                    if (result2 != null) {
                        try {
                            result2.close();
                        } catch (SQLException e) {
                            throw new DaoException(resultCloseErrorMessage, e);
                        }
                    }
                    if (statement2 != null) {
                        try {
                            statement2.close();
                        } catch (SQLException e) {
                            throw new DaoException(statementCloseErrorMessage, e);
                        }
                    }
                    releaseConnection(connection2);
                }                
            }
            return customers;
        } catch (SQLException e) {            
            throw new DaoException(getBannedCustomersErrorMessage, e);
        } finally {
            if (result1 != null) {
                try {
                    result1.close();
                } catch (SQLException e) {
                    throw new DaoException(resultCloseErrorMessage, e);
                }
            }
            if (statement1 != null) {
                try {
                    statement1.close();
                } catch (SQLException e) {
                    throw new DaoException(statementCloseErrorMessage, e);
                }
            }
            releaseConnection(connection1);
        }

    }

    /**
     *
     * @return not banned customers from database
     * @throws by.bsuir.project.entity.exception.DaoException
     */
    public List<Customer> getNotBannedCustomers() throws DaoException {
        List<Customer> customers = new ArrayList<>();
        Connection connection1 = null, connection2 = null;
        PreparedStatement statement1 = null, statement2 = null;
        ResultSet result1 = null, result2 = null;

        try {
            connection1 = getConnection();
            statement1 = connection1.prepareStatement(getNotBannedCustomersSQL);
            result1 = statement1.executeQuery();
            
                
            while (result1.next()) {
                Customer customer = new Customer();
                customer.setId(result1.getInt(PARAM_NAME_ID));
                customer.setLogin(result1.getString(PARAM_NAME_LOGIN));
                customer.setPassword(result1.getString(PARAM_NAME_PASSWORD));
                customer.setFirstName(result1.getString(PARAM_NAME_FIRST_NAME));
                customer.setSecondName(result1.getString(PARAM_NAME_SECOND_NAME));
                customer.setPhone(result1.getString(PARAM_NAME_PHONE));
                customer.setBanned((short) result1.getInt(PARAM_NAME_BANNED));
                
               
                try {
                    connection2 = getConnection();
                    statement2 = connection2.prepareStatement(getAddressSQL);
                    statement2.setInt(1, customer.getId());
                    result2 = statement2.executeQuery();
                    while (result2.next()) {
                        Address address = new Address();
                        address.setStreet(result2.getString(PARAM_NAME_STREET));
                        address.setHouse(result2.getString(PARAM_NAME_HOUSE));
                        address.setApartment(result2.getString(PARAM_NAME_APARTMENT));
                        customer.setAddress(address);
                    }                    
                    customers.add(customer);
                } catch (SQLException e) {                    
                    throw new DaoException(getNotBannedCustomersErrorMessage, e);
                } finally {
                    if (result2 != null) {
                        try {
                            result2.close();
                        } catch (SQLException e) {
                            throw new DaoException(resultCloseErrorMessage, e);
                        }
                    }
                    if (statement2 != null) {
                        try {
                            statement2.close();
                        } catch (SQLException e) {
                            throw new DaoException(statementCloseErrorMessage, e);
                        }
                    }
                    releaseConnection(connection2);
                }                
            }
            return customers;
        } catch (SQLException e) {            
            throw new DaoException(getNotBannedCustomersErrorMessage, e);
        } finally {
            if (result1 != null) {
                try {
                    result1.close();
                } catch (SQLException e) {
                    throw new DaoException(resultCloseErrorMessage, e);
                }
            }
            if (statement1 != null) {
                try {
                    statement1.close();
                } catch (SQLException e) {
                    throw new DaoException(statementCloseErrorMessage, e);
                }
            }
            releaseConnection(connection1);
        }

    }

    /**
     * @param customerId for ban
     * @throws by.bsuir.project.entity.exception.DaoException
     */
    public void banCustomer(int customerId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(banCustomerSQL);
            statement.setString(1, Integer.toString(customerId));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(banCustomerErrorMessage, e);
        } finally {
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

    /**
     * @param customerId for unban
     * @throws by.bsuir.project.entity.exception.DaoException
     */
    public void unbanCustomer(int customerId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(unbanCustomerSQL);
            statement.setString(1, Integer.toString(customerId));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(unbanCustomerErrorMessage, e);
        } finally {
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

    /**
     * @param customerId for check
     * @return
     * @throws by.bsuir.project.entity.exception.DaoException
     */
    public boolean isBanned(int customerId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        int isBanned = 0;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(isBannedSQL);
            statement.setString(1, Integer.toString(customerId));
            result = statement.executeQuery();
            if (result.next()) {
                isBanned = result.getInt(PARAM_NAME_BANNED);
            }
            return isBanned == 1;
        } catch (SQLException e) {
            throw new DaoException(isBannedCheckErrorMessage, e);
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    throw new DaoException(resultCloseErrorMessage, e);
                }
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
