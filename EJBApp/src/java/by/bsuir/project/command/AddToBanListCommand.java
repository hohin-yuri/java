
package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.CustomerDao;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddToBanListCommand implements ICommand{    
    private final String errorMessage = "ADD_TO_BAN_LIST_COMMAND_FAIL";
        
    /**      
     * 
     * @param request the HTTP request   
     * @param response the HTTP response     
     * @return page
     * @throws by.bsuir.project.entity.exception.CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws CommandException{                                                
        
        try {
            String customerId = request.getParameter(Parameters.CUSTOMER_ID);
            /*Ban customer*/
            CustomerDao.getInstance().banCustomer(Integer.parseInt(customerId));                                         
            request.getSession().setAttribute(Parameters.NOT_BANNED_CUSTOMERS, CustomerDao.getInstance().getNotBannedCustomers());                                                                
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CUSTOMERS_PAGE_PATH);         
        } catch (DaoException e) {
            throw new CommandException(errorMessage,e);
        }                                
    }      
}
