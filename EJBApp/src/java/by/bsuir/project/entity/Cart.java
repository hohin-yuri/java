package by.bsuir.project.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Cart implements Serializable{   
    private final List<Item> items = new ArrayList<>();    
    private int total = 0;
        
    
    public void addItem(Product product){
        boolean newItemType = true;

        for (Item item : items) {
            if (item.getProduct().getId() == product.getId()) {
                newItemType = false;
                item.incrementQuantity();                
                break;
            }
        }

        if (newItemType) {
            Item item = new Item(product);
            items.add(item);             
        }      
        updateTotal();
    }

    
    public void removeItem(Item p){
        items.remove(p);
        updateTotal();
    }

    
    public int getCartSize(){
        int size = 0;
        for (Item item: items){
            size+=item.getQuantity();             
        }
        return size;
    }

    
    public List<Item> getItems(){
        return items;
    }    
    
    
    public void update(int productId, int quantity) {       
        if (quantity >= 0) {
            Item item = null;
            for (Item itemInCart : items) {
                if (itemInCart.getProduct().getId() == productId) {
                    if (quantity != 0) {                        
                        itemInCart.setQuantity(quantity); 
                        updateTotal();
                    } else {                        
                        item = itemInCart;
                        break;
                    }
                }
            }
            if (item != null) {               
                items.remove(item);
                updateTotal();
            }
        }
    }

    
    public void clear() {
        items.clear();                
    }
    
    
    public void updateTotal(){
        total = 0;
        for(Item item : items){
           total+=item.getQuantity() * item.getProduct().getPrice();
        }
    }
    
    
    public int getTotal(){
       return this.total;    
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.items);
        hash = 17 * hash + this.total;
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
        final Cart other = (Cart) obj;
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        return this.total == other.total;
    }

    @Override
    public String toString() {
        return "Cart{" + "items=" + items + ", total=" + total + '}';
    }

    
}
