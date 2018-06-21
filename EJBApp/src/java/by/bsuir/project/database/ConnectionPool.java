package by.bsuir.project.database;


import by.bsuir.project.entity.exception.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import by.bsuir.project.resource.ConfigurationManager;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private static volatile ConnectionPool instance;    
    private int capacity;
    private String userName;
    private String password;
    private String url;
    private static final ReentrantLock lock = new ReentrantLock();
    private BlockingQueue<Connection> connections;
    
    
    public static ConnectionPool getInstance() throws DatabaseException {        
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();                                                            
                }
            } finally {
                lock.unlock();
            }
        }        
        return instance;
    }
    
    
    public void init() throws DatabaseException{                
            ConfigurationManager appManager = ConfigurationManager.getInstance();
            this.capacity = Integer.parseInt(appManager.getProperty(ConfigurationManager.COUNT_CONNECTIONS));
            this.userName = appManager.getProperty(ConfigurationManager.USER_NAME);
            this.password = appManager.getProperty(ConfigurationManager.PASSWORD);
            this.url = appManager.getProperty(ConfigurationManager.URL);                                                     
            this.connections = new ArrayBlockingQueue<>(capacity);
                        
            try {
                Class.forName(appManager.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME)).newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {                
                throw new DatabaseException("CREATE_DRIVER_OBJECT_ERROR", e);                           
            }
                        
            for (int i = 0; i < capacity; i++){
               try {                    
                   connections.add(DriverManager.getConnection(url, userName, password));
               } catch (SQLException e) {                   
                   throw new DatabaseException("POOL_INIT_ERROR", e);                           
               }
            }                                                          
    }
    
    
    public Connection getConnection() throws DatabaseException{        
        Connection connection = null;
        try {
            connection = connections.take();                                     
        } catch (InterruptedException e) {            
            throw new DatabaseException("POOL_GET_CONNECTION_ERROR", e);
        }
        return connection;  
    }

    
    public void releaseConnection(Connection connection) throws DatabaseException{       
            if (connection == null){
                throw new NullPointerException();
            }
            else{
                try {
                    connections.put(connection);
                } catch (InterruptedException e) {                    
                    throw new DatabaseException("CONNECTION_RELEASE_ERROR", e);
                }
            }              
    }
    
    
    private void clearConnectionQueue() throws SQLException, DatabaseException{
        Connection connection;                 
        
        if (capacity > connections.size()) {
            lock.lock();
            try {
                wait(10000);
            }                                   
            catch (InterruptedException e) {   
                throw new DatabaseException("INTERRUPTED_ERROR", e);
            }
            finally {
                lock.unlock();
                while ((connection = connections.poll()) != null) {                          
                    connection.close();                        
                }
            }                        
        }
        	        
    }
    
    
    public void closePool() throws DatabaseException {
        try{            
            if (instance!= null) {                   
                instance.clearConnectionQueue();
                instance = null;				
            }
        } catch (SQLException e){
            throw new DatabaseException("POOL_CLOSE_ERROR", e);
        }
    }

}
