package co.simplon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import co.simplon.dto.CartDTO;
import co.simplon.service.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        CartDTO cart = cartService.getCurrentCart();
        model.addAttribute("cart", cart);
        return "cart/index.html";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam String productId, @RequestParam int quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam String productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/save")
    public String saveCart() {
        cartService.saveCart();
        return "redirect:/cart";
    }
    
}
