package by.bsuir.projectdao.interf;

import by.bsuir.projectentity.Customer;
import by.bsuir.projectentity.exception.DaoException;
import java.util.List;


public interface CustomerDaoInterface {
    
    public List<Customer> getAllCustomers() throws DaoException;                                                   
}
