package by.bsuir.project.resource;

import java.util.ResourceBundle;


public class ConfigurationManager {
    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "by/bsuir/project/resource.config";
    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
    public static final String URL = "URL";
    public static final String USER_NAME = "USER_NAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String COUNT_CONNECTIONS = "COUNT_CONNECTIONS";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String INDEX_PAGE_PATH = "INDEX_PAGE_PATH";    
    public static final String ORDERS_PAGE_PATH = "ORDERS_PAGE_PATH";
    public static final String CUSTOMERS_PAGE_PATH = "CUSTOMERS_PAGE_PATH";    
    public static final String CATEGORIES_PAGE_PATH = "CATEGORIES_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String ADMIN_LOGIN_PAGE_PATH = "ADMIN_LOGIN_PAGE_PATH";
    public static final String CART_PAGE_PATH = "CART_PAGE_PATH";
    public static final String PURCHASE_PAGE_PATH = "PURCHASE_PAGE_PATH";
    public static final String CONFIRMATION_PAGE_PATH = "CONFIRMATION_PAGE_PATH";
    public static final String CATEGORY_PAGE_PATH = "CATEGORY_PAGE_PATH";
    public static final String BANLIST_PAGE_PATH = "BANLIST_PAGE_PATH";          
    public static final String REGISTER_PAGE_PATH = "REGISTER_PAGE_PATH";          


    
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
