
package by.bsuir.project.dao.interf;

import by.bsuir.project.entity.Customer;
import by.bsuir.project.entity.exception.DaoException;

by.bsuir.project.
public interface UserEnterDaoInterface {
    by.bsuir.project.
    public Customer checkCredentials(String login, String password) throws DaoException;
    public boolean checkLogin(String login) throws DaoException;
    public int addCustomer(Customer customer) throws DaoException;
}
