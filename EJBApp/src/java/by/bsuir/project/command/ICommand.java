package by.bsuir.project.command;

import by.bsuir.project.entity.exception.CommandException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ICommand {
 
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException;
}
