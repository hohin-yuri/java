package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.ProductDao;
import by.bsuir.project.entity.Product;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ProductsCommand implements ICommand {

    private final String errorMessage = "PRODUCTS_COMMAND_FAIL";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException {

        /*Getting current session*/
        HttpSession session = request.getSession();
        try {
            /*Category is chosen check*/
            if ((request.getParameter(Parameters.CATEGORY) != null || (session.getAttribute(Parameters.CATEGORY) != null))) {
                /*Save chosen category in session*/
                if (request.getParameter(Parameters.CATEGORY) != null) {
                    /*Getting products of the chosen category from database */
                    List<Product> products = ProductDao.getInstance().getProducts(Integer.parseInt(request.getParameter("category")));
                    /*Update products in session*/
                    session.removeAttribute(Parameters.PRODUCTS);
                    session.setAttribute(Parameters.PRODUCTS, products);
                    session.setAttribute(Parameters.CATEGORY, Integer.parseInt(request.getParameter(Parameters.CATEGORY)));
                }
            }

            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CATEGORY_PAGE_PATH);
        } catch (DaoException e) {
            throw new CommandException(errorMessage, e);
        }
    }
}
