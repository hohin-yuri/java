
package by.bsuir.project.entity;

import java.util.Date;


public class Courier {
    private int id;
    private String firstName;
    private String secondName;
    private Date startWork;
    private boolean withCar;

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

    public boolean isWithCar() {
        return withCar;
    }

    public void setWithCar(boolean withCar) {
        this.withCar = withCar;
    }
    
    
}
