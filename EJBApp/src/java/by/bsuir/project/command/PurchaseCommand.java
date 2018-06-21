
package by.bsuir.project.command;

import by.bsuir.project.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PurchaseCommand implements ICommand{

    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.PURCHASE_PAGE_PATH);       
    }
    
}
