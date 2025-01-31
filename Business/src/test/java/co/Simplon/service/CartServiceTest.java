package co.Simplon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.Simplon.Entity.Cart;
import co.Simplon.Entity.Product;
import co.Simplon.Entity.ProductCart;
import co.Simplon.Repository.CartRepository;
import co.Simplon.Repository.ProductCartRepository;
import co.Simplon.Repository.ProductRepository;
import co.Simplon.converter.ProductConverter;
import co.Simplon.dto.CartDTO;
import co.Simplon.dto.ProductDTO;
import co.Simplon.service.impl.CartServiceImpl;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductCartRepository productCartRepository;

    @Mock
    private ProductConverter productConverter;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart testCart;
    private Product testProduct;
    private ProductCart testProductCart;
    private ProductDTO testProductDTO;

    @BeforeEach
    void setUp() {
        testCart = new Cart();
        testCart.setCart_id("cart123");
        testCart.setSave_date(null);

        testProduct = new Product();
        testProduct.setProduct_id("prod123");
        testProduct.setName("Test Product");
        testProduct.setPrice("99.99");

        testProductCart = new ProductCart();
        testProductCart.setCart(testCart);
        testProductCart.setProduct(testProduct);
        testProductCart.setQuantity(2);

        testProductDTO = new ProductDTO();
        testProductDTO.setProductId("prod123");
        testProductDTO.setName("Test Product");
        testProductDTO.setPrice("99.99");
    }

    @Test
    void getCurrentCart_WithNoExistingCart_ShouldCreateNewCart() {
        // Arrange
        when(cartRepository.save(any(Cart.class))).thenReturn(testCart);
        when(productCartRepository.findByCartId(testCart.getCart_id())).thenReturn(Arrays.asList());

        // Act
        CartDTO result = cartService.getCurrentCart();

        // Assert
        assertNotNull(result);
        assertEquals(testCart.getCart_id(), result.getCartId());
        assertTrue(result.getItems().isEmpty());
        assertEquals(0.0, result.getTotalPrice());
        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    void addToCart_WithValidProduct_ShouldAddToCart() {
        // Arrange
        when(cartRepository.save(any(Cart.class))).thenReturn(testCart);
        when(productRepository.findById("prod123")).thenReturn(Optional.of(testProduct));
        when(productCartRepository.save(any(ProductCart.class))).thenReturn(testProductCart);
        when(productCartRepository.findByCartId(anyString())).thenReturn(Arrays.asList(testProductCart));
        when(productConverter.entityToDto(testProduct)).thenReturn(testProductDTO);

        // Act
        CartDTO result = cartService.addToCart("prod123", 2);

        // Assert
        assertNotNull(result);
        assertFalse(result.getItems().isEmpty());
        assertEquals(1, result.getItems().size());
        assertEquals(testProductDTO, result.getItems().get(0).getProduct());
        assertEquals(2, result.getItems().get(0).getQuantity());
        assertEquals(199.98, result.getTotalPrice());
        verify(productCartRepository).save(any(ProductCart.class));
    }

    @Test
    void addToCart_WithInvalidProduct_ShouldReturnNull() {
        // Arrange
        when(productRepository.findById("invalid")).thenReturn(Optional.empty());

        // Act
        CartDTO result = cartService.addToCart("invalid", 1);

        // Assert
        assertNull(result);
        verify(productCartRepository, never()).save(any());
    }

    @Test
    void removeFromCart_WithExistingProduct_ShouldRemoveFromCart() {
        // Arrange
        when(cartRepository.save(any(Cart.class))).thenReturn(testCart);
        when(productCartRepository.findByCartId(anyString())).thenReturn(Arrays.asList());

        // Act
        CartDTO result = cartService.removeFromCart("prod123");

        // Assert
        assertNotNull(result);
        assertTrue(result.getItems().isEmpty());
        assertEquals(0.0, result.getTotalPrice());
        verify(productCartRepository).deleteByCartAndProductId(anyString(), eq("prod123"));
    }

    @Test
    void saveCart_WithExistingCart_ShouldSaveAndCreateNewCart() {
        // Arrange
        when(cartRepository.save(any(Cart.class))).thenReturn(testCart);
        when(productCartRepository.findByCartId(anyString())).thenReturn(Arrays.asList());

        // Act
        CartDTO result = cartService.saveCart();

        // Assert
        assertNotNull(result);
        verify(cartRepository, times(2)).save(any(Cart.class)); // Une fois pour la sauvegarde, une fois pour le nouveau panier
    }

    @Test
    void clearCart_WithExistingCart_ShouldClearCart() {
        // Act
        cartService.clearCart();

        // Assert
        verify(productCartRepository).deleteByCartId(anyString());
        verify(cartRepository).delete(any(Cart.class));
    }
} 