
package by.bsuir.project.entity;

import java.util.Date;


public class Cook {
    private int id;
    private String firstName;
    private String secondName;
    private Date startWork;
    private Date sanitaryCheck;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getStartWork() {
        return startWork;
    }

    public void setStartWork(Date startWork) {
        this.startWork = startWork;
    }

    public Date getSanitaryCheck() {
        return sanitaryCheck;
    }

    public void setSanitaryCheck(Date sanitaryCheck) {
        this.sanitaryCheck = sanitaryCheck;
    }
    
    
}
