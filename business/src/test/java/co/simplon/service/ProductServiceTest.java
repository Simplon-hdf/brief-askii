package co.simplon.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.simplon.entity.Product;
import co.simplon.repository.ProductRepository;
import co.simplon.converter.ProductConverter;
import co.simplon.dto.ProductDTO;
import co.simplon.service.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductConverter productConverter;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct;
    private ProductDTO testProductDTO;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setProductId("test-product-id");
        testProduct.setName("Test Product");
        testProduct.setPrice("10.00");

        testProductDTO = new ProductDTO();
        testProductDTO.setProductId("123");
        testProductDTO.setName("Test Product");
        testProductDTO.setPrice("99.99");
    }

    @Test
    void getAllProducts_ShouldReturnAllProducts() {
        // Arrange
        List<Product> products = Collections.singletonList(testProduct);
        when(productRepository.findAll()).thenReturn(products);
        when(productConverter.entityToDto(testProduct)).thenReturn(testProductDTO);

        // Act
        List<ProductDTO> result = productService.getAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testProductDTO, result.get(0));
        verify(productRepository).findAll();
        verify(productConverter).entityToDto(testProduct);
    }

    @Test
    void getProductById_WithExistingId_ShouldReturnProduct() {
        // Arrange
        when(productRepository.findById("123")).thenReturn(Optional.of(testProduct));
        when(productConverter.entityToDto(testProduct)).thenReturn(testProductDTO);

        // Act
        ProductDTO result = productService.getProductById("123");

        // Assert
        assertNotNull(result);
        assertEquals(testProductDTO, result);
        verify(productRepository).findById("123");
        verify(productConverter).entityToDto(testProduct);
    }

    @Test
    void getProductById_WithNonExistingId_ShouldReturnNull() {
        // Arrange
        when(productRepository.findById("999")).thenReturn(Optional.empty());

        // Act
        ProductDTO result = productService.getProductById("999");

        // Assert
        assertNull(result);
        verify(productRepository).findById("999");
        verify(productConverter, never()).entityToDto(any());
    }

    @Test
    void createProduct_ShouldSaveAndReturnProduct() {
        // Arrange
        when(productConverter.dtoToEntity(testProductDTO)).thenReturn(testProduct);
        when(productRepository.save(testProduct)).thenReturn(testProduct);
        when(productConverter.entityToDto(testProduct)).thenReturn(testProductDTO);

        // Act
        ProductDTO result = productService.createProduct(testProductDTO);

        // Assert
        assertNotNull(result);
        assertEquals(testProductDTO, result);
        verify(productConverter).dtoToEntity(testProductDTO);
        verify(productRepository).save(testProduct);
        verify(productConverter).entityToDto(testProduct);
    }

    @Test
    void updateProduct_WithExistingId_ShouldUpdateAndReturnProduct() {
        // Arrange
        when(productRepository.existsById("123")).thenReturn(true);
        when(productConverter.dtoToEntity(testProductDTO)).thenReturn(testProduct);
        when(productRepository.save(testProduct)).thenReturn(testProduct);
        when(productConverter.entityToDto(testProduct)).thenReturn(testProductDTO);

        // Act
        ProductDTO result = productService.updateProduct("123", testProductDTO);

        // Assert
        assertNotNull(result);
        assertEquals(testProductDTO, result);
        verify(productRepository).existsById("123");
        verify(productConverter).dtoToEntity(testProductDTO);
        verify(productRepository).save(testProduct);
        verify(productConverter).entityToDto(testProduct);
    }

    @Test
    void updateProduct_WithNonExistingId_ShouldReturnNull() {
        // Arrange
        when(productRepository.existsById("999")).thenReturn(false);

        // Act
        ProductDTO result = productService.updateProduct("999", testProductDTO);

        // Assert
        assertNull(result);
        verify(productRepository).existsById("999");
        verify(productConverter, never()).dtoToEntity(any());
        verify(productRepository, never()).save(any());
        verify(productConverter, never()).entityToDto(any());
    }

    @Test
    void deleteProduct_ShouldCallRepository() {
        // Act
        productService.deleteProduct("123");

        // Assert
        verify(productRepository).deleteById("123");
    }
} 