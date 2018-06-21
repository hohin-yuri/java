package by.bsuir.project.entity;

import java.util.Objects;


public class FoodSupplier {

    private int id;
    private String companyTitle;
    private String companyPhone;
    private Address companyAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public Address getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Address companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.companyTitle);
        hash = 29 * hash + Objects.hashCode(this.companyPhone);
        hash = 29 * hash + Objects.hashCode(this.companyAddress);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FoodSupplier other = (FoodSupplier) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.companyTitle, other.companyTitle)) {
            return false;
        }
        if (!Objects.equals(this.companyPhone, other.companyPhone)) {
            return false;
        }
        return Objects.equals(this.companyAddress, other.companyAddress);
    }

}
