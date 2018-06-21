package by.bsuir.project.dao.implementation;

import by.bsuir.project.dao.interf.AbstractDao;
import by.bsuir.project.dao.interf.OrderDaoInterface;
import by.bsuir.project.entity.Item;
import by.bsuir.project.entity.Order;
import by.bsuir.project.entity.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OrderDao extends AbstractDao implements OrderDaoInterface{  
    private final String addOrderSQL = "INSERT INTO `ORDER` (order_date, phone, customer_id, address_id) VALUES (?,?,?,?)";
    private final String addAddressSQL = "INSERT INTO `ADDRESS` (street, house, apartment) VALUES (?,?,?)";
    private final String addProductsSQL = "INSERT INTO PRODUCT_IN_ORDER (order_id, product_id, quantity) VALUES (?,?,?)";
    private final String getAllOrdersSQL = "SELECT order_id,customer_id,first_name,second_name,order_date,product_id,quantity FROM 4elements.customer, 4elements.order as r INNER JOIN product_in_order d ON r.id = d.order_id where customer.id=customer_id";
    private final String addOrderErrorMessage = "ADD_ORDER_ORDER_DAO_FAIL";
    private final String getAllOrdersErrorMessage = "GET_ALL_ORDERS_ORDER_DAO_FAIL";        
    private final String statementCloseErrorMessage = "STATEMENT_CLOSE_GET_ALL_ORDERS_ORDER_DAO_FAIL";        
    private final String PARAM_NAME_ORDER_ID = "order_id";
    private final String PARAM_NAME_ORDER_DATE = "order_date";
    private final String PARAM_NAME_CUSTOMER_ID = "customer_id";
    private final String PARAM_NAME_FIRST_NAME = "first_name";    
    private final String PARAM_NAME_SECOND_NAME = "second_name";    
    private final String PARAM_NAME_PRODUCT_ID = "product_id";    
    private final String PARAM_NAME_QUANTITY = "quantity";
    private static OrderDao instance;
            
    private OrderDao(){
    } 
    

    public static OrderDao getInstance(){
        if (instance==null){
           instance = new OrderDao();
        }
        return instance;
    }
    

    @Override
    public void addOrder(Order order) throws DaoException{                                                      
        Connection connection;
        PreparedStatement statement; 
        ResultSet generatedKeys;
        int orderId = 0, addressId = 0;
        try{            
            connection = getConnection();
            
            statement = connection.prepareStatement(addAddressSQL,Statement.RETURN_GENERATED_KEYS);
            try {                                                                                                                              
                    statement.setString(1, order.getAddress().getStreet());
                    statement.setString(2, order.getAddress().getHouse());
                    statement.setString(3, order.getAddress().getApartment());
                    statement.executeUpdate();                     

                    generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        addressId = generatedKeys.getInt(1);
                    }            
            }
            finally{                                            
                if (statement != null) {
                   statement.close();
                }               
            }                                        
            
            statement = connection.prepareStatement(addOrderSQL,Statement.RETURN_GENERATED_KEYS);
            try {                                                                                      
                    statement.setTimestamp(1,order.getDate());                
                    statement.setString(2, order.getPhone());                    
                    statement.setInt(3, order.getCustomerId());                                                                
                    statement.setInt(4, addressId);
                    statement.executeUpdate();                     

                    generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                    }            
            }
            finally{                                            
                if (statement != null) {
                   statement.close();
                }               
            }    
            try{                
                if (orderId!=0){                    
                    for(Item item : order.getProducts()){                                                 
                        statement = connection.prepareStatement(addProductsSQL);                                 
                        statement.setInt(1,orderId);
                        statement.setInt(2,item.getProduct().getId());
                        statement.setInt(3,item.getQuantity());
                        statement.executeUpdate();                                                                                      
                    }
                }
            }
            finally{                   
                if (statement != null) {
                   statement.close();
                }
                releaseConnection(connection);
            }          
            
            
            
            
            
        }
        catch (SQLException e) {
            throw new DaoException(addOrderErrorMessage, e);                
        }
    }


    @Override
    public List<Order> getAllOrders() throws DaoException{                                                       
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result;            
        final List<Order> orders = new ArrayList<>();
        
        
        try {            
            connection = getConnection();            
            statement = connection.prepareStatement(getAllOrdersSQL);                        
            result = statement.executeQuery();                      

            Order order = new Order();
            while (result.next())
            {                
                int id = result.getInt(PARAM_NAME_ORDER_ID);

                if (order.getId()!=id){
                    if (order.getId()!=0){
                      orders.add(order);
                      order = new Order();
                    }                                                                    
                    order.setId(id);
                    order.setCustomerId(result.getInt(PARAM_NAME_CUSTOMER_ID));              
                    order.setCustomerFirstName(result.getString(PARAM_NAME_FIRST_NAME));              
                    order.setCustomerSecondName(result.getString(PARAM_NAME_SECOND_NAME));              
                    order.setDate(result.getTimestamp(PARAM_NAME_ORDER_DATE));   
                }
                int productId = result.getInt(PARAM_NAME_PRODUCT_ID);
                Item item = new Item(ProductDao.getInstance().getProduct(productId));
                item.setQuantity(result.getInt(PARAM_NAME_QUANTITY));
                order.getProducts().add(item);                            
            }
            if (order.getId()!=0) orders.add(order);                                  


            return orders;    
        }   
        catch (SQLException e) {
            throw new DaoException(getAllOrdersErrorMessage, e);            
        }
        finally{                       
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
