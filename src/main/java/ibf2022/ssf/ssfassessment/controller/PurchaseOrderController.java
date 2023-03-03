package ibf2022.ssf.ssfassessment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.ssf.ssfassessment.model.Address;
import ibf2022.ssf.ssfassessment.model.Cart;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping(path={"/", "index"})
public class PurchaseOrderController {

    HashMap<String, Integer> cart = new HashMap<String, Integer>();
    String item;
    Integer quantity;
    
    @GetMapping
    public String getView(Model model) {
        model.addAttribute("cart", new Cart());
        return "index";
    }

    @PostMapping("/add")
    public String addItem(Model model, HttpSession sess, @Valid Cart cart, BindingResult result) {

        if (result.hasErrors()) 
            return "/";

        
        cart.addToCart(item, quantity);

        sess.setAttribute("cart", cart);
        
        model.addAttribute("cart", cart);
        return "redirect:/index";
    }

    @GetMapping(path="/shippingaddress")
    public String getAddress(Model model, HttpSession sess) {
        model.addAttribute("address", new Address());
        return "view2";
    }

    @PostMapping(path="/checkout")


    //method to add to cart
    public Map<String, Integer> addToCart(String item, Integer quantity) {
        
        if (null == cart.get(item)) {
            cart.put(item, quantity);
        } else if (cart.containsKey(item)) {
            cart.put(item, (this.quantity+quantity));
        }

        return cart;
    }
}


