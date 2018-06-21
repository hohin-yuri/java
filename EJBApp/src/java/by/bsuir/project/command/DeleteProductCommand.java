package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.ProductDao;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteProductCommand implements ICommand {

    private final String errorMessage = "DELETE_PRODUCT_COMMAND_FAIL";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        try {
            String product = request.getParameter(Parameters.PRODUCT_ID);
            
            /*Request parameter check*/
            if (product != null && product.length() > 1) {
                product = product.substring(0, product.length() - 1);
                int productId = Integer.parseInt(product);
                
                /*Removing product from database*/
                ProductDao.getInstance().deleteProduct(productId);
                request.getSession().setAttribute(Parameters.PRODUCTS, ProductDao.getInstance().getAllProducts());
            }
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CATEGORIES_PAGE_PATH);
        } catch (DaoException e) {
            throw new CommandException(errorMessage, e);
        }
    }

}
