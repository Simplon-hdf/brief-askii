package co.Simplon.converter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.Simplon.Entity.Product;
import co.Simplon.dto.ProductDTO;

class ProductConverterTest {
    
    private ProductConverter converter;
    
    @BeforeEach
    void setUp() {
        converter = new ProductConverter();
    }
    
    @Test
    void entityToDto_WithValidProduct_ShouldConvertAllFields() {
        // Arrange
        Product product = new Product();
        product.setProduct_id("123");
        product.setName("Test Product");
        product.setPrice("99.99");
        
        // Act
        ProductDTO result = converter.entityToDto(product);
        
        // Assert
        assertNotNull(result);
        assertEquals("123", result.getProductId());
        assertEquals("Test Product", result.getName());
        assertEquals("99.99", result.getPrice());
    }
    
    @Test
    void entityToDto_WithNullProduct_ShouldReturnNull() {
        // Act
        ProductDTO result = converter.entityToDto(null);
        
        // Assert
        assertNull(result);
    }
    
    @Test
    void dtoToEntity_WithValidDTO_ShouldConvertAllFields() {
        // Arrange
        ProductDTO dto = new ProductDTO();
        dto.setProductId("123");
        dto.setName("Test Product");
        dto.setPrice("99.99");
        
        // Act
        Product result = converter.dtoToEntity(dto);
        
        // Assert
        assertNotNull(result);
        assertEquals("123", result.getProduct_id());
        assertEquals("Test Product", result.getName());
        assertEquals("99.99", result.getPrice());
    }
    
    @Test
    void dtoToEntity_WithNullDTO_ShouldReturnNull() {
        // Act
        Product result = converter.dtoToEntity(null);
        
        // Assert
        assertNull(result);
    }
} 