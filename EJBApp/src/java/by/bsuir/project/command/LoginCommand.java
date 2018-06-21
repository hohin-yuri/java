package by.bsuir.project.command;

import by.bsuir.project.dao.implementation.UserEnterDao;
import by.bsuir.project.entity.Customer;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.logic.GetCategoriesLogic;
import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.MessageManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginCommand implements ICommand {

    private final String errorMessage = "LOGIN_COMMAND_FAIL";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException {

        /*Getting request parameters*/
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);
        Customer customer;
        /*Form check*/
        String error = Validator.validateLoginForm(login, password, request);
        try {
            if (error == null) {
                /*Getting user info and login check*/
                if ((customer = UserEnterDao.getInstance().checkCredentials(login, password)) != null) {
                    request.getSession().setAttribute(Parameters.CUSTOMER, customer);
                    /*Getting categories from database*/
                    GetCategoriesLogic.getCategories(request);
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
                } else /*User login fail*/ {
                    request.setAttribute(Parameters.ERROR, MessageManager.getInstance().getProperty(MessageManager.ERROR_LOGIN));
                }
            } else {
                /*Form is not valid*/
                request.setAttribute(Parameters.ERROR, error);
            }
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        } catch (DaoException e) {
            throw new CommandException(errorMessage, e);
        }
    }

}
