package ibf2022.ssf.ssfassessment.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.Min;

public class Cart  {

    private String item;
    @Min(value=1, message="You must add at least 1 item")
    private Integer quantity;
    private Map<String, Integer> cart;

    //getters and setters
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Map<String, Integer> getCart() {
        return cart;
    }

    public void setCart(Map<String, Integer> cart) {
        this.cart = cart;
    }

    public Cart() {
    }

    public Cart(String item, @Min(value = 1, message = "You must add at least one item") Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item [item=" + item + ", quantity=" + quantity + "]";
    }

    public Map<String, Integer> addToCart(String item, Integer quantity) {
        
        if (null == cart.get(item)) {
            cart.put(item, quantity);
        } else if (cart.containsKey(item)) {
            cart.put(item, (this.quantity+quantity));
        }

        return cart;
    }

    //converts HashMap to List to further processing
    public List<String> convertToList(Map<String, Integer> cart) {
        //assuming user's cart get pass on as HashMap<String, Integer> with Item as key, Quantity as value
        List<String> items = new LinkedList<>(cart.keySet());
        return items;
    }

    

}
