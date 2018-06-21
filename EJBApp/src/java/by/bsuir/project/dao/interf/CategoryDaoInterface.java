package by.bsuir.project.dao.interf;

import by.bsuir.project.entity.Category;
import by.bsuir.project.entity.exception.DaoException;
import java.util.List;


public interface CategoryDaoInterface {
    
    public List<Category> getAllCategories() throws DaoException;                                                   
}
