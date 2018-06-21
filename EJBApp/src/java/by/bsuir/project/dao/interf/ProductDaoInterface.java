
package by.bsuir.project.dao.interf;

import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.entity.Product;
import java.util.List;


public interface ProductDaoInterface {
    by.bsuir.project.
    public Product getProduct(int id) throws DaoException;
    by.bsuir.project.
    public void deleteProduct(int id) throws DaoException;
    by.bsuir.project.
    public String addProduct(Product product) throws DaoException;
    by.bsuir.project.
    public List<Product> getProducts(int id) throws DaoException;
    by.bsuir.project.
    public List<Product> getAllProducts() throws DaoException;
}
