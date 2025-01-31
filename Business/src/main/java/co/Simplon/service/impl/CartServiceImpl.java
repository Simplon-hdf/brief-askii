package co.Simplon.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.Simplon.Entity.Cart;
import co.Simplon.Entity.Product;
import co.Simplon.Entity.ProductCart;
import co.Simplon.Repository.CartRepository;
import co.Simplon.Repository.ProductCartRepository;
import co.Simplon.Repository.ProductRepository;
import co.Simplon.converter.ProductConverter;
import co.Simplon.dto.CartDTO;
import co.Simplon.dto.CartItemDTO;
import co.Simplon.service.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductCartRepository productCartRepository;
    private final ProductConverter productConverter;
    
    private Cart currentCart;

    public CartServiceImpl(CartRepository cartRepository, 
                         ProductRepository productRepository,
                         ProductCartRepository productCartRepository,
                         ProductConverter productConverter) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productCartRepository = productCartRepository;
        this.productConverter = productConverter;
    }

    @Override
    public CartDTO getCurrentCart() {
        if (currentCart == null) {
            initializeNewCart();
        }
        return convertToDTO(currentCart);
    }

    @Override
    public CartDTO addToCart(String productId, int quantity) {
        if (currentCart == null) {
            initializeNewCart();
        }

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            return null;
        }

        ProductCart productCart = new ProductCart();
        productCart.setCart(currentCart);
        productCart.setProduct(product);
        productCart.setQuantity(quantity);

        productCartRepository.save(productCart);
        
        return convertToDTO(currentCart);
    }

    @Override
    public CartDTO removeFromCart(String productId) {
        if (currentCart == null) {
            return null;
        }

        productCartRepository.deleteByCartAndProductId(currentCart.getCartId(), productId);
        return convertToDTO(currentCart);
    }

    @Override
    public CartDTO saveCart() {
        if (currentCart == null) {
            return null;
        }

        currentCart.setSaveDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        cartRepository.save(currentCart);
        
        Cart savedCart = currentCart;
        initializeNewCart();
        
        return convertToDTO(savedCart);
    }

    @Override
    public void clearCart() {
        if (currentCart != null) {
            productCartRepository.deleteByCartId(currentCart.getCartId());
            cartRepository.delete(currentCart);
            currentCart = null;
        }
    }

    private void initializeNewCart() {
        currentCart = new Cart();
        currentCart.setCartId(UUID.randomUUID().toString());
        cartRepository.save(currentCart);
    }

    private CartDTO convertToDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setCartId(cart.getCartId());
        dto.setSaveDate(cart.getSaveDate());

        List<CartItemDTO> items = new ArrayList<>();
        double totalPrice = 0.0;

        List<ProductCart> productCarts = productCartRepository.findByCartId(cart.getCartId());
        for (ProductCart pc : productCarts) {
            CartItemDTO item = new CartItemDTO();
            item.setProduct(productConverter.entityToDto(pc.getProduct()));
            item.setQuantity(pc.getQuantity());
            
            double itemPrice = Double.parseDouble(pc.getProduct().getPrice()) * pc.getQuantity();
            item.setItemTotal(itemPrice);
            totalPrice += itemPrice;

            items.add(item);
        }

        dto.setItems(items);
        dto.setTotalPrice(totalPrice);

        return dto;
    }
} 