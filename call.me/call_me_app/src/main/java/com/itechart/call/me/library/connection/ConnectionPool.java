package com.itechart.call.me.library.connection;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** Extension of ConnectionPoolInterf
 * Provides connection from connection
 * pool
 *
 * Use:
 * Connection connection =
 *      ConnectionPool.getInstance().getConnection();
 *
 * @author Hohin Yury
 */
public class ConnectionPool
        implements ConnectionPoolInterf {

    private static ConnectionPool instance;
    /**
     * Implimentation of singleton pattern
     */
    public static ConnectionPool getInstance(){
        if (instance != null){
            return instance;
        }
        instance = new ConnectionPool();
        return instance;
    }

    private DataSource dataSource = getDataSource();

    /**
     * This method impliments ConnectionPoolInterf
     * @return Connection instance
     */
    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Inits conection pool
     * to provide connection with db
     * @return Connection instance
     */
    private static DataSource getDataSource() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(resourceBundle.getString("URL"));
        dataSource.setDriverClassName(resourceBundle.getString("DATABASE_DRIVER_NAME"));
        dataSource.setUsername(resourceBundle.getString("USER_NAME"));
        dataSource.setPassword(resourceBundle.getString("PASSWORD"));
        dataSource.addDataSourceProperty( "cachePrepStmts" , "true" );
        dataSource.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        dataSource.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        return dataSource;
    }


}
