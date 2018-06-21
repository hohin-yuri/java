package by.bsuir.project.command;

import by.bsuir.project.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginPageCommand implements ICommand{
            
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){                                 
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);       
    }        
}
