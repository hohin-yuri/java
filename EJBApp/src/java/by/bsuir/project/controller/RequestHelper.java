package by.bsuir.project.controller;



import by.bsuir.project.command.*;
import by.bsuir.project.resource.Parameters;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.http.HttpServletRequest;


public class RequestHelper {
    private static volatile RequestHelper instance = null;    
    private final HashMap<String, ICommand> commands = new HashMap<>();   
    private static final ReentrantLock lock = new ReentrantLock();
    

    public static RequestHelper getInstance() {        
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new RequestHelper();                                                            
                }
            } finally {
                lock.unlock();
            }
        }        
        return instance;
    }

    private RequestHelper() {               
        commands.put(Parameters.ADMIN_LOGIN_COMMAND, new AdminLoginCommand());        
        commands.put(Parameters.USER_LOGIN_COMMAND, new LoginCommand());
        commands.put(Parameters.USER_REGISTER_COMMAND, new RegisterCommand());
        commands.put(Parameters.LOGOUT_COMMAND, new LogoutCommand());  
        commands.put(Parameters.CHANGE_LANG_COMMAND, new ChangeLangCommand());                        
        commands.put(Parameters.INDEX_COMMAND, new CategoriesCommand());          
        commands.put(Parameters.CONFIRM_COMMAND, new ConfirmationCommand());  
        commands.put(Parameters.ADD_PRODUCT_COMMAND, new AddProductCommand());  
        commands.put(Parameters.SAVE_PRODUCT_COMMAND, new SaveProductCommand());        
        commands.put(Parameters.DELETE_PRODUCT_COMMAND, new DeleteProductCommand());  
        commands.put(Parameters.UPDATE_CART_COMMAND, new UpdateCartCommand());
        commands.put(Parameters.ADD_TO_CART_COMMAND, new AddToCartCommand());                
        commands.put(Parameters.UNCART_ALL_COMMAND, new UncartAllCommand());        
        commands.put(Parameters.CLEAR_CART_COMMAND, new ClearCartCommand());       
        commands.put(Parameters.VIEW_CART_COMMAND, new ViewCartCommand());        
        commands.put(Parameters.PURCHASE_COMMAND, new PurchaseCommand());  
        commands.put(Parameters.GET_BANLIST_COMMAND, new GetBanListCommand()); 
        commands.put(Parameters.GET_ORDERS_COMMAND, new OrdersCommand()); 
        commands.put(Parameters.ADD_TO_BANLIST_COMMAND, new AddToBanListCommand());
        commands.put(Parameters.REMOVE_FROM_BANLIST_COMMAND, new RemoveFromBanListCommand());
        commands.put(Parameters.GET_NOT_BANNED_CUSTOMERS_COMMAND, new GetNotBannedCustomersCommand());
        commands.put(Parameters.GET_PRODUCTS_COMMAND, new ProductsCommand());        
        commands.put(Parameters.GET_CATEGORIES_COMMAND, new CategoriesCommand());        
        commands.put(Parameters.LOGIN_PAGE_COMMAND, new LoginPageCommand());         
        commands.put(Parameters.REGISTER_PAGE_COMMAND, new RegisterPageCommand());         
    }    

  
    public ICommand getCommand(HttpServletRequest request) { 
        String action = request.getParameter(Parameters.COMMAND);
     
        ICommand command = commands.get(action);
        
        if (command == null) {      
            command = commands.get(Parameters.INDEX_COMMAND);
        }
        return command;
    } 

}
