package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.UserEnterDao;
import by.bsuir.project.entity.Customer;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterCommand implements ICommand {
    private final String errorMessage = "REGISTER_COMMAND_FAIL";
   
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException {
        
        Customer customer = new Customer();
        customer.setFirstName(request.getParameter(Parameters.FIRST_NAME));
        customer.setSecondName(request.getParameter(Parameters.SECOND_NAME));
        customer.setPhone(request.getParameter(Parameters.PHONE));
        customer.getAddress().setStreet(request.getParameter(Parameters.STREET));
        customer.getAddress().setHouse(request.getParameter(Parameters.HOUSE));
        customer.getAddress().setApartment(request.getParameter(Parameters.APARTMENT));
        customer.setLogin(request.getParameter(Parameters.LOGIN));
        customer.setPassword(request.getParameter(Parameters.PASSWORD));        
        String error = null;
        try {
            error = Validator.validateRegisterForm(customer);
        } catch (DaoException e) {
            throw new CommandException(errorMessage, e);
        }
                             
        try {
            if (error == null) {               
               int id = UserEnterDao.getInstance().addCustomer(customer);
               if (id!=0){
                   customer.setId(id);
                   request.getSession().setAttribute(Parameters.CUSTOMER, customer);
               }               
            } else {
                /*User info is not valid*/
                request.setAttribute(Parameters.ERROR, error);     
                return ConfigurationManager.getInstance ().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            }
            return ConfigurationManager.getInstance ().getProperty(ConfigurationManager.INDEX_PAGE_PATH);       
        } catch (DaoException e) {                        
            throw new CommandException(errorMessage, e);
        }
    }
}

 
  
