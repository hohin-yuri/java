
package by.bsuir.project.dao.interf;

import by.bsuir.project.database.ConnectionPool;
import by.bsuir.project.entity.exception.DaoException;
import by.bsuir.project.entity.exception.DatabaseException;
import java.sql.Connection;




public abstract class AbstractDao {            
    
    
    public ConnectionPool getConnectionPool() throws DaoException {        
        try {
            return ConnectionPool.getInstance();
        } catch (DatabaseException e) {
            throw new DaoException(e); 
        }
    }    
    
    
    public Connection getConnection() throws DaoException{
        try {
            return getConnectionPool().getConnection();
        } catch (DatabaseException e) {
            throw new DaoException(e); 
        }
    }
    
    
    public void releaseConnection(Connection connection) throws DaoException{
        try {
            getConnectionPool().releaseConnection(connection);
        } catch (DatabaseException e) {
            throw new DaoException(e); 
        }
    }
            
}
