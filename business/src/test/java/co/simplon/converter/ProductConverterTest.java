package co.simplon.converter;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.simplon.entity.Product;
import co.simplon.converter.ProductConverter;
import co.simplon.dto.ProductDTO;

class ProductConverterTest {
    
    private ProductConverter converter;
    
    @BeforeEach
    void setUp() {
        converter = new ProductConverter();
    }
    
    @Test
    void entityToDto_ShouldConvertProductToDTO() {
        // Arrange
        Product product = new Product();
        product.setProductId("test-id");
        product.setName("Test Product");
        product.setPrice("10.00");
        
        // Act
        ProductDTO result = converter.entityToDto(product);
        
        // Assert
        assertNotNull(result);
        assertEquals("test-id", result.getProductId());
        assertEquals("Test Product", result.getName());
        assertEquals("10.00", result.getPrice());
    }
    
    @Test
    void entityToDto_WithNullProduct_ShouldReturnNull() {
        // Act
        ProductDTO result = converter.entityToDto(null);
        
        // Assert
        assertNull(result);
    }
    
    @Test
    void dtoToEntity_ShouldConvertDTOToProduct() {
        // Arrange
        ProductDTO dto = new ProductDTO();
        dto.setProductId("test-id");
        dto.setName("Test Product");
        dto.setPrice("10.00");
        
        // Act
        Product result = converter.dtoToEntity(dto);
        
        // Assert
        assertNotNull(result);
        assertEquals("test-id", result.getProductId());
        assertEquals("Test Product", result.getName());
        assertEquals("10.00", result.getPrice());
    }
    
    @Test
    void dtoToEntity_WithNullDTO_ShouldReturnNull() {
        // Act
        Product result = converter.dtoToEntity(null);
        
        // Assert
        assertNull(result);
    }
} 