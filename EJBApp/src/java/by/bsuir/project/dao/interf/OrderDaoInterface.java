
package by.bsuir.project.dao.interf;

import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.entity.Order;
import java.util.List;


public interface OrderDaoInterface {
    
    public void addOrder(Order order) throws DaoException;
    
    public List<Order> getAllOrders() throws DaoException;                                      
}
