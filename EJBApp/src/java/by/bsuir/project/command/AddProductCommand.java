package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.ProductDao;
import by.bsuir.project.entity.Product;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddProductCommand implements ICommand {

    private final String errorMessage = "ADD_PRODUCT_COMMAND_FAIL";

    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException {
        /*Request parameters check*/
        String error = Validator.validateProductForm(request.getParameter(Parameters.CATEGORY_ID),
                request.getParameter(Parameters.TITLE),
                request.getParameter(Parameters.DESCRIPTION),
                request.getParameter(Parameters.WEIGHT),
                request.getParameter(Parameters.PRICE),
                request.getParameter(Parameters.CALORIES),
                request);
        if (error != null) {
            request.setAttribute(Parameters.ERROR, error);
        } else {
            String categoryId = request.getParameter(Parameters.CATEGORY_ID);
            String title = request.getParameter(Parameters.TITLE);
            String description = request.getParameter(Parameters.DESCRIPTION);
            String weight = request.getParameter(Parameters.WEIGHT);
            String price = request.getParameter(Parameters.PRICE);
            String calories = request.getParameter(Parameters.CALORIES);

            Product product = new Product();
            product.setCategoryId(Integer.parseInt(categoryId));
            product.setTitle(title);
            product.setDescription(description);
            product.setWeight(weight);
            product.setPrice(Integer.parseInt(price));
            product.setCalories(Integer.parseInt(calories));

            /*Addition product into database*/
            try {
                if ((error = ProductDao.getInstance().addProduct(product)) == null) {
                    request.setAttribute(Parameters.PRODUCTS, ProductDao.getInstance().getAllProducts());
                } else {
                    request.setAttribute(Parameters.ERROR, error);
                }
                request.setAttribute(Parameters.PRODUCTS, ProductDao.getInstance().getAllProducts());
            } catch (DaoException e) {
                throw new CommandException(errorMessage, e);
            }
        }        
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CATEGORIES_PAGE_PATH);
    }

}
