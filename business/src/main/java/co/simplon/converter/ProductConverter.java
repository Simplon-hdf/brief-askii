package co.simplon.converter;

import org.springframework.stereotype.Component;

import co.simplon.entity.Product;
import co.simplon.dto.ProductDTO;

@Component
public class ProductConverter {
    
    public ProductDTO entityToDto(Product entity) {
        if (entity == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setProductId(entity.getProductId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public Product dtoToEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Product entity = new Product();
        entity.setProductId(dto.getProductId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }
} 