package by.bsuir.project.controller;

import by.bsuir.project.command.ICommand;
import by.bsuir.project.entity.exception.CommandException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet(name = "ControllerServlet", 
            loadOnStartup = 1,
            urlPatterns = { 
                            "/index",
                            "/admin",                                                                                                                                                                                                   
                            "/cart",                            
                            "/check",
                            "/lang"})                             
public class ControllerServlet extends HttpServlet { 
    private final static Logger logger = LogManager.getLogger(ControllerServlet.class.getName());
    private final String errorMessage = "CONTROLLER_SERVLET_EXCEPTION";
    
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        processRequest(request, response);        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{            
        processRequest(request, response);         
    }

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException{            
        RequestHelper requestHelper = RequestHelper.getInstance();        
        String page;
        ICommand command;
        RequestDispatcher dispatcher;
        BufferedWriter out = null;
        try  
        {
            FileWriter fstream = new FileWriter("out.txt", true); //true tells to append data.
            out = new BufferedWriter(fstream);
            out.write("\nsue");
        }
        catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        finally
        {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
        
        try {
            command = requestHelper.getCommand(request);
            page = command.execute(request, response);
            dispatcher = getServletContext().getRequestDispatcher(page);                            
            dispatcher.forward(request, response);
        } catch (ServletException | IOException | CommandException e) {
            logger.error(errorMessage, e); 
            throw new ServletException(e);
        }        
    }        
}
