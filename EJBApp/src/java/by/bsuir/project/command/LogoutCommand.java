package by.bsuir.project.command;
import by.bsuir.project.entity.exception.CommandException;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.logic.GetCategoriesLogic;
import by.bsuir.project.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutCommand implements ICommand {
    private final String errorMessage = "LOGOUT_COMMAND_FAIL";            
    
   
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
            throws CommandException{      
        
        /*Invalidate current session*/
        request.getSession().invalidate();
        try {
            /*Getting categories from database*/
            GetCategoriesLogic.getCategories(request);        
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
        } catch (DaoException e) {
            throw new CommandException(errorMessage, e);
        }        
    }
}
