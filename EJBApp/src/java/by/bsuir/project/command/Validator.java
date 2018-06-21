package by.bsuir.project.command;
import by.bsuir.project.dao.implementation.UserEnterDao;
import by.bsuir.project.entity.Customer;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.resource.MessageManager;
import javax.servlet.http.HttpServletRequest;


public final class Validator {    
    public static String validatePurchaseForm(  String phone,
                                                String street,
                                                String house,
                                                String apartment,
                                                HttpServletRequest request) {
        
        String errorMessage = null; 
        
        if (phone == null
                || phone.equals("")
                || phone.length() < 7)  {
            
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_PHONE);                        
        }else{
        if (street == null
                || street.equals("")
                || street.length() > 45)  {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_STREET);
        }else{
        if (house == null
                || house.equals("")
                || house.length() > 5)  {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_HOUSE);                        
        }else{
        if (apartment == null
                || apartment.equals("")
                || apartment.length() > 5) {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_APARTMENT);                        
        }}}}

        return errorMessage;
    }

    public static String validateRegisterForm( Customer customer) throws DaoException {
        
        String errorMessage = null;                 
        String firstName = customer.getFirstName();
        String secondName = customer.getSecondName();
        String login = customer.getLogin();
        String password = customer.getPassword();
        String phone = customer.getPhone();
        String street = customer.getAddress().getStreet();
        String house = customer.getAddress().getHouse();
        String apartment = customer.getAddress().getApartment();
        
        
        
        if ( firstName == null
                || firstName.equals("")
                || firstName.length() < 2
                || firstName.length() > 15)  {
            
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_FIRST_NAME);                        
        }else{
        if (secondName == null
                || secondName.equals("")
                || secondName.length() < 2
                || secondName.length() > 15)  {
            
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_SECOND_NAME);                        
        }else{
        if (phone == null
                || phone.equals("")
                || phone.length() < 7)  {
            
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_PHONE);                        
        }else{
        if (street == null
                || street.equals("")
                || street.length() > 45)  {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_STREET);
        }else{
        if (house == null
                || house.equals("")
                || house.length() > 5)  {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_HOUSE);               
        }else{
        if (apartment == null
                || apartment.equals("")
                || apartment.length() > 5) {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_APARTMENT); 
        }else{ 
        if (login == null
                || login.equals("")
                || login.length() < 5)  {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_LOGIN);                        
        }else{
        if (UserEnterDao.getInstance().checkLogin(login))  {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_DUPLICATE_LOGIN);                        
        }else{    
        if (password == null
            || password.equals("")
            || password.length() < 8)  {
        errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_PASSWORD);                        
        }
        }}}}}}}}

        return errorMessage;
    }

    
    public static String validateLoginForm(String login,
                                     String password,                                    
                                     HttpServletRequest request) {

        String errorMessage = null;        
        
        if (login == null
                || login.equals("")
                || login.length() > 45){
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_LOGIN);                        ;                        
        } else{       
        if (password == null
                || password.equals("")
                || password.length() > 45) {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_LOGIN);                        
        }}        
        return errorMessage;
    }
    
    public static String validateProductForm(   String categoryId,
                                                String title,
                                                String description,
                                                String weight,
                                                String price,
                                                String calories,
                                                HttpServletRequest request) {
        
        String errorMessage = null; 
        
        if (categoryId == null
                || categoryId.equals("")
                || categoryId.length() < 1  
                || categoryId.length() > 3) {            
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_CATEGORY_ID);                        
        } else{ 
        if (title == null
                || title.equals("")
                || title.length() > 45)  {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_TITLE);
        } else{
        if (description == null
                || description.equals("")
                || description.length() > 255)  {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_DESCRIPTION);                        
        } else{
        if (weight == null
                || weight.equals("")
                || weight.length() > 10) {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_WEIGHT);                        
        } else{
        if (price == null
                || price.equals("")
                || price.length() > 10) {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_PRICE);                        
        } else{
        if (calories == null
                || calories.equals("")
                || calories.length() > 10) {
            errorMessage = MessageManager.getInstance().getProperty(MessageManager.ERROR_CALORIES);                        
        }}}}}}
        return errorMessage;
    }
    
    public static int validateProductAmount( String amountStr, HttpServletRequest request){        
        int amount;
        
        try  
        {  
          amount = Integer.parseInt(amountStr);
        }  
        catch(NumberFormatException e)  {
            amount = -1;
        }      
        
        if (amountStr == null
                || amountStr.equals("")
                || amountStr.length() > 4){            
            amount = -1;
        }
        
        return amount;
    }
}