
package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.CustomerDao;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetNotBannedCustomersCommand implements ICommand{
    private final String errorMessage = "GET_NOT_BANNED_CUSTOMERS_COMMAND_FAIL";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws CommandException{                               
        
        try {
            /*Getting not banned users from database*/
            request.getSession().setAttribute(Parameters.NOT_BANNED_CUSTOMERS, CustomerDao.getInstance().getNotBannedCustomers());
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CUSTOMERS_PAGE_PATH);         
        } catch (DaoException e) {
            throw new CommandException(errorMessage,e);
        }        
    } 
}
