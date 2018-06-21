package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.CustomerDao;
import by.bsuir.project.dao.implementation.ProductDao;
import by.bsuir.project.entity.Cart;
import by.bsuir.project.entity.Customer;
import by.bsuir.project.entity.Product;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.MessageManager;
import by.bsuir.project.resource.Parameters;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaveProductCommand implements ICommand {

    private final String errorMessage = "ADD_TO_CART_COMMAND_FAIL";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException {

        /*Getting saved product id */
        String saveProductId = request.getParameter(Parameters.PRODUCT);
        Integer addProductId = 0;
        if (request.getSession().getAttribute(Parameters.PRODUCT_ID) != null) {
            /*Getting added product id */
            addProductId = (Integer) request.getSession().getAttribute(Parameters.PRODUCT_ID);
        }

        /*Are added and saved products equal check*/
        if (!Objects.equals(addProductId, Integer.parseInt(saveProductId))) {
            /*Product isn't added*/
            if (addProductId == 0) {
                request.setAttribute(Parameters.ERROR, MessageManager.getInstance().getProperty(MessageManager.CHOOSE_NOTIFICATION));
            } /*Other product is saved*/ else {
                request.setAttribute(Parameters.ERROR, MessageManager.getInstance().getProperty(MessageManager.SAVE_NOTIFICATION));
            }

        } else {

            /*Getting current customer and his cart*/
            Customer customer = (Customer) request.getSession().getAttribute(Parameters.CUSTOMER);
            Cart cart = (Cart) request.getSession().getAttribute(Parameters.CART);

            try {
                /*User is banned check*/
                if (CustomerDao.getInstance().isBanned(customer.getId()) != false) {
                    request.setAttribute(Parameters.ERROR, MessageManager.getInstance().getProperty(MessageManager.BANNED));
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
                } else {
                    /*Cart is exist check*/
                    if (cart == null) {
                        cart = new Cart();
                    }

                    /*Get product with {@addProductId}*/
                    Product product = ProductDao.getInstance().getProduct(addProductId);
                    /*Addition product into database*/
                    cart.addItem(product);

                    request.getSession().setAttribute(Parameters.CART, cart);
                    request.getSession().removeAttribute(Parameters.PRODUCT_ID);
                }
            } catch (DaoException e) {
                throw new CommandException(errorMessage, e);
            }
        }

        return (String) request.getSession().getAttribute(Parameters.CURRENT_JSP);
    }

}
