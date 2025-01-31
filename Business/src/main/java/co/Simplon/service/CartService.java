package co.Simplon.service;

import co.Simplon.dto.CartDTO;

public interface CartService {
    CartDTO getCurrentCart();
    CartDTO addToCart(String productId, int quantity);
    CartDTO removeFromCart(String productId);
    CartDTO saveCart();
    void clearCart();
} 