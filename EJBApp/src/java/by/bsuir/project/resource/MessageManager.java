package by.bsuir.project.resource;

import java.util.Locale;
import java.util.ResourceBundle;


public class MessageManager {
    private static MessageManager instance;
    private ResourceBundle resourceBundle;
    private static String currentLocale = "en-EN";
    public static final String BUNDLE_NAME = "by/bsuir/project/resource.messages";
    public static final String LOCALE = "message.LOCALE";
    public static final String ID = "message.ID";
    public static final String WELCOME = "message.WELCOME";
    public static final String NO_SUCH_CATEGORY = "message.NO_SUCH_CATEGORY";
    public static final String UNBAN = "message.UNBAN";
    public static final String SAVE_NOTIFICATION="message.SAVE_NOTIFICATION";
    public static final String CHOOSE_NOTIFICATION="message.CHOOSE_NOTIFICATION";
    public static final String DESCRIPTION = "message.DESCRIPTION";
    public static final String AMOUNT = "message.AMOUNT";
    public static final String ERROR_FIRST_NAME = "message.ERROR_FIRST_NAME";
    public static final String ERROR_SECOND_NAME = "message.ERROR_SECOND_NAME";
    public static final String ERROR_PHONE = "message.ERROR_PHONE";
    public static final String ERROR_STREET = "message.ERROR_STREET";
    public static final String ERROR_HOUSE = "message.ERROR_HOUSE";
    public static final String ERROR_APARTMENT = "message.ERROR_APARTMENT";
    public static final String ERROR_CATEGORY_ID = "message.ERROR_CATEGORY_ID";
    public static final String ERROR_TITLE = "message.ERROR_TITLE";
    public static final String ERROR_DESCRIPTION = "message.ERROR_DESCRIPTION";
    public static final String ERROR_WEIGHT = "message.ERROR_WEIGHT";
    public static final String ERROR_PRICE = "message.ERROR_PRICE";
    public static final String ERROR_LOGIN = "message.ERROR_LOGIN";
    public static final String ERROR_PASSWORD = "message.ERROR_PASSWORD";
    public static final String ERROR_CALORIES = "message.ERROR_CALORIES";
    public static final String EMPTY_CART = "message.EMPTY_CART";
    public static final String ERROR_DUPLICATE_LOGIN = "message.ERROR_DUPLICATE_LOGIN";
    public static final String BANNED = "message.BANNED";
    public static final String SAVE = "message.SAVE";   
    public static final String LOGIN = "message.LOGIN";
    public static final String PASSWORD = "message.PASSWORD";
    public static final String ENTER = "message.ENTER";
    public static final String LOGOUT = "message.LOGOUT";
    public static final String WELCOME_USER = "message.WELCOME_USER";
    public static final String SHOP_TITLE = "message.SHOP_TITLE";
    public static final String CART = "message.CART";
    public static final String DENOMINATION = "message.DENOMINATION";
    public static final String CALORIES = "message.CALORIES";
    public static final String PRICE = "message.PRICE";
    public static final String WEIGHT = "message.WEIGHT";
    public static final String COUNT = "message.COUNT"; 
    public static final String OPTIONS = "message.OPTIONS";
    public static final String HOME = "message.HOME";
    public static final String UNCART = "message.UNCART";
    public static final String UNCART_ALL = "message.UNCARTALL";
    public static final String BACK_TO_STORE = "message.BACKTOSTORE";
    public static final String BUY = "message.BUY";
    public static final String TOTAL ="message.TOTAL";
    public static final String SIGN = "message.SIGN";
    public static final String THANKS = "message.THANKS";    
    public static final String LOGIN_FORM = "message.LOGIN_FORM";
    public static final String CREDENTIALS = "message.CREDENTIALS";
    public static final String BACK_TO_CART = "message.BACKTOCART";
    public static final String NAME = "message.NAME";
    public static final String FIRST_NAME = "message.FIRST_NAME";
    public static final String SECOND_NAME = "message.SECOND_NAME";
    public static final String PHONE = "message.PHONE";
    public static final String STREET = "message.STREET";
    public static final String HOUSE = "message.HOUSE";
    public static final String APARTMENT = "message.APARTMENT";
    public static final String SUBMIT = "message.SUBMIT";
    public static final String LOGIN_WARN = "message.LOGIN_WARN";
    public static final String PRODUCTS_TITLE = "message.PRODUCTS_TITLE";
    public static final String ADD = "message.ADD";
    public static final String LANG = "message.LANG";
    public static final String REGISTER = "message.REGISTER";

    
    public static MessageManager getInstance() {
            if (instance == null) {
                instance = createLocalizableBundle(currentLocale);
            }
        return instance;
    }

    
    private static MessageManager createLocalizableBundle(String locale) {
        Locale tempLocale = Locale.forLanguageTag(locale);
        if(instance == null) {
            instance = new MessageManager();
        }
        instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, tempLocale);
        return instance;
    }

    
    public String getProperty(String key) {
        return resourceBundle.getString(key);
    }

    
    public static void changeLocale(String newLocale) {
        if(!newLocale.equals(currentLocale))
        {
            currentLocale = newLocale;
            instance = createLocalizableBundle(currentLocale);
        }
    }
}
