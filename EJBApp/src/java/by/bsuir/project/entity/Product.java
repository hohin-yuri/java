package by.bsuir.project.entity;

import java.io.Serializable;
import java.util.Objects;


public class Product implements Serializable{
    private int id;
    private String title;
    private String description;
    private String weight;
    private int price;
    private int calories;
    private int categoryId;    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.weight);
        hash = 59 * hash + this.price;
        hash = 59 * hash + this.calories;
        hash = 59 * hash + this.categoryId;
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
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.weight, other.weight)) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (this.calories != other.calories) {
            return false;
        }
        return this.categoryId == other.categoryId;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", title=" + title + ", description=" + description + ", weight=" + weight + ", price=" + price + ", calories=" + calories + ", categoryId=" + categoryId + '}';
    }
    
    
}
