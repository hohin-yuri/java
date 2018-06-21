package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.CustomerDao;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetBanListCommand implements ICommand {

    private final String errorMessage = "GET_BAN_LIST_COMMAND_FAIL";

  
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws CommandException {
        
        try {
            /*Getting banned users from database*/
            request.getSession().setAttribute(Parameters.BANNED_CUSTOMERS, CustomerDao.getInstance().getBannedCustomers());
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.BANLIST_PAGE_PATH);
        } catch (DaoException e) {
            throw new CommandException(errorMessage, e);
        }
    }
}
