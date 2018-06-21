package by.bsuir.project.command;

import by.bsuir.project.resource.ConfigurationManager;
import by.bsuir.project.resource.Parameters;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class UncartAllCommand implements ICommand {    

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){  
        /*Removing cart*/
        request.getSession().removeAttribute(Parameters.CART);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.CART_PAGE_PATH);
    }
    
}
