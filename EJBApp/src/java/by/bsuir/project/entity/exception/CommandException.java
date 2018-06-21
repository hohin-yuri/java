
package by.bsuir.project.entity.exception;


public class CommandException extends Exception{    
    private String errorCode = "Unknown exception code";
     
    
    public CommandException(Exception error){
        this.errorCode = error.getMessage();
    }
    
    
    public CommandException(String message, Exception error){
        super(message);
        this.errorCode = error.getMessage();
    }
     
    
    public String getErrorCode(){
        return this.errorCode;
    }
}
