package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.OrderDao;
import by.bsuir.project.entity.Cart;
import by.bsuir.project.entity.Customer;
import by.bsuir.project.entity.Order;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConfirmationCommand implements ICommand {

    private final String errorMessage = "CONFIRMATION_COMMAND_FAIL";

  
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        /*Getting user's cart*/
        Cart cart = (Cart) request.getSession().getAttribute(Parameters.CART);
        /*Request parameters check*/
        String errorForm = Validator.validatePurchaseForm(request.getParameter(Parameters.PHONE),
                request.getParameter(Parameters.STREET),
                request.getParameter(Parameters.HOUSE),
                request.getParameter(Parameters.APARTMENT),
                request);

        if (errorForm != null) {
            request.setAttribute(Parameters.ERROR, errorForm);
            return (String) request.getSession().getAttribute(Parameters.CURRENT_JSP);
        }

        Customer customer = (Customer) request.getSession().getAttribute(Parameters.CUSTOMER);

        try {
            /*Making ner order*/
            Order order = new Order();
            order.setCustomerId(customer.getId());
            java.sql.Timestamp time = new java.sql.Timestamp(new java.util.Date().getTime());
            order.setDate(time);
            order.setPhone(request.getParameter(Parameters.PHONE));
            order.getAddress().setStreet(request.getParameter(Parameters.STREET));
            order.getAddress().setHouse(request.getParameter(Parameters.HOUSE));
            order.getAddress().setApartment(request.getParameter(Parameters.APARTMENT));
            order.setProducts(cart.getItems());
            /*Addition new order into database*/
            OrderDao.getInstance().addOrder(order);

            request.getSession().setAttribute(Parameters.CART, null);

        } catch (DaoException e) {
            throw new CommandException(errorMessage, e);
        }

        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CONFIRMATION_PAGE_PATH);
    }
}
