
package by.bsuir.project.entity.exception;


public class DaoException extends Exception{    
    private String errorCode = "Unknown exception code";
     
    
    public DaoException(Exception error){
        this.errorCode = error.getMessage();
    }
    
    
    public DaoException(String message, Exception error){
        super(message);
        this.errorCode = error.getMessage();
    }
     
    
    public String getErrorCode(){
        return this.errorCode;
    }
}
