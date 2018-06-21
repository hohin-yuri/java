package by.bsuir.project.dao.implementation;

import by.bsuir.project.dao.interf.AbstractDao;
import by.bsuir.project.dao.interf.ProductDaoInterface;
import by.bsuir.project.entity.Product;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.MessageManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDao extends AbstractDao implements ProductDaoInterface{  
    private final String addProductSQL = "INSERT INTO product(title,description,weight,price,calories,category_id) VALUES(?,?,?,?,?,?)";
    private final String getProductSQL = "SELECT * FROM 4elements.product WHERE product.id = ?";
    private final String deleteProductSQL = "DELETE FROM 4elements.product WHERE product.id = ?";
    private final String getProductsSQL = "SELECT * FROM 4elements.product WHERE product.category_id = ?";
    private final String checkCategorySQL = "SELECT * FROM 4elements.category WHERE id = ?";
    private final String getAllProductsSQL = "SELECT * FROM 4elements.product ORDER BY category_id";
    private final String addProductErrorMessage = "ADD_PRODUCT_PRODUCT_DAO_FAIL";
    private final String getProductErrorMessage = "GET_PRODUCT_PRODUCT_DAO_FAIL";    
    private final String deleteProductErrorMessage = "DELETE_PRODUCT_PRODUCT_DAO_FAIL";
    private final String getProductsErrorMessage = "GET_PRODUCTS_PRODUCT_PRODUCT_DAO_FAIL";
    private final String getAllProductsErrorMessage = "GET_ALL_PRODUCTS_PRODUCT_DAO_FAIL";
    private final String statementCloseErrorMessage = "STATEMENT_CLOSE_GET_ALL_PRODUCTS_PRODUCT_DAO_FAIL";
    private final String resultCloseErrorMessage= "RESULT_CLOSE_GET_ALL_PRODUCTS_PRODUCT_DAO_FAIL";
    private final String PARAM_NAME_ID = "id";
    private final String PARAM_NAME_TITLE = "title";
    private final String PARAM_NAME_DESCRIPTION = "description";
    private final String PARAM_NAME_WEIGHT = "weight";
    private final String PARAM_NAME_PRICE = "price";
    private final String PARAM_NAME_CALORIES = "calories";    
    private final String PARAM_NAME_CATEGORY_ID = "category_id";     
    private static ProductDao instance;
            
    private ProductDao(){
    } 
    

    public static ProductDao getInstance(){
        if (instance==null){
           instance = new ProductDao();
        }
        return instance;
    }
    

    @Override
    public String addProduct(Product product) throws DaoException{     
        Boolean categoryExists = false;
        Connection connection;
        PreparedStatement statement = null;      
        ResultSet result = null;
        
            try{
                connection = getConnection();
                statement = connection.prepareStatement(checkCategorySQL);
                statement.setInt(1, product.getCategoryId());
                result = statement.executeQuery();
                if (result.next()){
                    categoryExists = true;
                }
            }
            catch (SQLException e) {
                throw new DaoException(addProductErrorMessage, e);                
            }
            finally{  
                if (result != null) {
                    try {
                        result.close();
                    } catch (SQLException e) {
                        throw new DaoException(addProductErrorMessage, e);  
                    }
                } 
                
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        throw new DaoException(statementCloseErrorMessage, e);  
                    }
                }               
            } 
            try{
                if (categoryExists){
                    statement = connection.prepareStatement(addProductSQL);
                    statement.setString(1,product.getTitle());                
                    statement.setString(2,product.getDescription());                
                    statement.setString(3,product.getWeight());                
                    statement.setInt(4,product.getPrice());                
                    statement.setInt(5,product.getCalories());                
                    statement.setInt(6,product.getCategoryId());                
                    statement.executeUpdate();                                                                                                                                             
                }
                else{
                    return MessageManager.getInstance().getProperty(MessageManager.NO_SUCH_CATEGORY);
                }
            }
            catch (SQLException e) {
                throw new DaoException(addProductErrorMessage, e);                
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
            return null;
        }        

    @Override
    public Product getProduct(int id) throws DaoException{                                              
        Connection connection = null;
        PreparedStatement statement = null;    
        ResultSet result = null;
        
                   
            try {            
                connection = getConnection();                
                statement = connection.prepareStatement(getProductSQL); 
                statement.setInt(1, id);
                result = statement.executeQuery();
                result.next();
                      
                Product product = new Product();
                product.setId(result.getInt(PARAM_NAME_ID));
                product.setTitle(result.getString(PARAM_NAME_TITLE));
                product.setDescription(result.getString(PARAM_NAME_DESCRIPTION));
                product.setWeight(result.getString(PARAM_NAME_WEIGHT));
                product.setPrice(result.getInt(PARAM_NAME_PRICE));
                product.setCalories(result.getInt(PARAM_NAME_CALORIES));
                product.setCategoryId(id);
                return product; 
            }
            catch (SQLException e) {
                throw new DaoException(getProductErrorMessage, e);    
            }
            finally{ 
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
                         

    @Override
    public void deleteProduct(int id) throws DaoException{                                                      
        Connection connection = null;
        PreparedStatement statement = null;                        
        try {            
            connection = getConnection();                
            statement = connection.prepareStatement(deleteProductSQL); 
            statement.setInt(1, id);
            statement.executeUpdate();                
        }
        catch (SQLException e) {
            throw new DaoException(deleteProductErrorMessage, e);    
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
        
           

    @Override
    public List<Product> getProducts(int id) throws DaoException{                                                      
        Connection connection= null;
        PreparedStatement statement = null;    
        ResultSet result = null;
        List<Product> products = new ArrayList<>();
                           
        try {            
            connection = getConnection();                
            statement = connection.prepareStatement(getProductsSQL); 
            statement.setInt(1, id);
            result = statement.executeQuery();                  
            while (result.next())
            {             
                Product product = new Product();
                product.setId(result.getInt(PARAM_NAME_ID));
                product.setTitle(result.getString(PARAM_NAME_TITLE));
                product.setDescription(result.getString(PARAM_NAME_DESCRIPTION));
                product.setWeight(result.getString(PARAM_NAME_WEIGHT));
                product.setPrice(result.getInt(PARAM_NAME_PRICE));
                product.setCalories(result.getInt(PARAM_NAME_CALORIES));
                product.setCategoryId(id);
                products.add(product);                         
            } 
            return products; 
        }
        catch (SQLException e) {
            throw new DaoException(getProductsErrorMessage, e);    
        }
        finally{        
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
        
           

    @Override
    public List<Product> getAllProducts() throws DaoException{                                                      
        Connection connection = null;
        PreparedStatement statement = null;    
        ResultSet result = null;
        List<Product> products = new ArrayList<>();                          
        try {            
            connection = getConnection();                
            statement = connection.prepareStatement(getAllProductsSQL); 
            result = statement.executeQuery();                                
            while (result.next())
            {             
                Product product = new Product();
                product.setId(result.getInt(PARAM_NAME_ID));
                product.setTitle(result.getString(PARAM_NAME_TITLE));
                product.setDescription(result.getString(PARAM_NAME_DESCRIPTION));
                product.setWeight(result.getString(PARAM_NAME_WEIGHT));
                product.setPrice(result.getInt(PARAM_NAME_PRICE));
                product.setCalories(result.getInt(PARAM_NAME_CALORIES));
                product.setCategoryId(result.getInt(PARAM_NAME_CATEGORY_ID));
                products.add(product);                         
            } 
            return products; 
        }
        catch (SQLException e) {
            throw new DaoException(getAllProductsErrorMessage, e);    
        }
        finally{        
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
