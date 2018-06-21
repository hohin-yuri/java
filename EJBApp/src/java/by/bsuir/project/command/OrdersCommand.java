package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.OrderDao;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OrdersCommand implements ICommand {

    private final String errorMessage = "ORDERS_COMMAND_FAIL";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException {

        try {
            /*Getting orders from database*/
            request.getSession().setAttribute(Parameters.ORDERS, OrderDao.getInstance().getAllOrders());
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ORDERS_PAGE_PATH);
        } catch (DaoException e) {
            throw new CommandException(errorMessage, e);
        }
    }

}
