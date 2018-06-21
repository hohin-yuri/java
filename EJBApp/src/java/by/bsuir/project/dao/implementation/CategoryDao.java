package by.bsuir.project.dao.implementation;

import by.bsuir.project.dao.interf.AbstractDao;
import by.bsuir.project.dao.interf.CategoryDaoInterface;
import by.bsuir.project.entity.Category;
import by.bsuir.project.entity.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoryDao extends AbstractDao implements CategoryDaoInterface{ 
    private final String getAllCategoriesSQL = "SELECT * FROM 4elements.category";
    private final String getAllCategoriesErrorMessage = "GET_ALL_CATEGORIES_CATEGORY_DAO_FAIL";
    private final String resultCloseErrorMessage = "RESULT_CLOSE_GET_ALL_CATEGORIES_CATEGORY_DAO_FAIL";
    private final String statementCloseErrorMessage = "STATEMENT_CLOSE_GET_ALL_CATEGORIES_CATEGORY_DAO_FAIL";
    private final String PARAM_NAME_ID = "id";
    private final String PARAM_NAME_TITLE = "title";
    private static CategoryDao instance;
            
    private CategoryDao(){
    } 
    
   
    public static CategoryDao getInstance(){
        if (instance==null){
           instance = new CategoryDao();
        }
        return instance;
    }
    
    /**
     *     
     * @return all categories from database
     * @throws by.bsuir.project.entity.exception.DaoException
     */
    @Override
    public List<Category> getAllCategories() throws DaoException{                                                   
        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;                       
        ResultSet result = null;
        
        try {
            connection = getConnection();                
            statement = connection.prepareStatement(getAllCategoriesSQL);
            result = statement.executeQuery();
            while (result.next())
            {
                Category category = new Category();
                category.setId((short) result.getInt(PARAM_NAME_ID));
                category.setTitle(result.getString(PARAM_NAME_TITLE));
                categories.add(category);
            }
            return categories;        
        } 
        catch (SQLException e) {
            throw new DaoException(getAllCategoriesErrorMessage, e);    
        }
        finally {    
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
