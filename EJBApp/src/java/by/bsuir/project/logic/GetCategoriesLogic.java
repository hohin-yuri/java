
package by.bsuir.project.logic;

import by.bsuir.project.dao.implementation.CategoryDao;
import by.bsuir.project.entity.Category;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.Parameters;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


public class GetCategoriesLogic {
    
    
    public static void getCategories(HttpServletRequest request) throws DaoException{
        
        List<Category> categories = CategoryDao.getInstance().getAllCategories();
        
        request.getSession().setAttribute(Parameters.CATEGORIES, categories);            
    }
}
