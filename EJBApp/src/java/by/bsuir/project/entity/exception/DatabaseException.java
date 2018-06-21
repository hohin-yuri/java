package by.bsuir.project.entity.exception;


public class DatabaseException extends Exception{   
    private String errorCode = "Unknown exception code";
     
    
    public DatabaseException(String message, Exception error){
        super(message);
        this.errorCode = error.getMessage();
    }
    
     
    public String getErrorCode(){
        return this.errorCode;
    }
    
}
