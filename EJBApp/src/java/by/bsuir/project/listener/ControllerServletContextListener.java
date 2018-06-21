package by.bsuir.project.listener;

import by.bsuir.project.controller.ControllerServlet;
import by.bsuir.project.database.ConnectionPool;
import by.bsuir.project.entity.exception.DatabaseException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebListener
public class ControllerServletContextListener implements ServletContextListener{
    private final static Logger logger = LogManager.getLogger(ControllerServlet.class.getName());
    private final String errorMessage = "CONTROLLER_SERVLET_LISTENERS_EXCEPTION";
    
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {       
        try {                   
            ConnectionPool.getInstance().init();
        } catch (DatabaseException e) {
            logger.info(errorMessage, e);
        }
        
    }

    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {        
        try {        
            ConnectionPool.getInstance().closePool();
        } catch (DatabaseException e) {
            logger.info(errorMessage, e);
        }
    }
    
}
