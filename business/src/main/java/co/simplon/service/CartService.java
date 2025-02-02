package co.simplon.service;

import co.simplon.dto.CartDTO;

public interface CartService {
    CartDTO getCurrentCart();
    CartDTO addToCart(String productId, int quantity);
    CartDTO removeFromCart(String productId);
    CartDTO saveCart();
    void clearCart();
} 