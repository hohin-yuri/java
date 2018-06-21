
package by.bsuir.project.logic;

import by.bsuir.project.dao.implementation.ProductDao;
import by.bsuir.project.entity.Product;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.Parameters;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


public class GetProductsLogic {
    
    
    public static void getProducts(HttpServletRequest request) throws DaoException{
        
        List<Product> products = ProductDao.getInstance().getAllProducts();
        
        request.setAttribute(Parameters.PRODUCTS, products);            
    }
}
