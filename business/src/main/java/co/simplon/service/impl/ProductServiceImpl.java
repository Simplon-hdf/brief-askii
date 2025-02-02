package co.simplon.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.entity.Product;
import co.simplon.repository.ProductRepository;
import co.simplon.converter.ProductConverter;
import co.simplon.dto.ProductDTO;
import co.simplon.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductServiceImpl(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(String id) {
        return productRepository.findById(id)
                .map(productConverter::entityToDto)
                .orElse(null);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productConverter.dtoToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return productConverter.entityToDto(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        if (productRepository.existsById(id)) {
            Product product = productConverter.dtoToEntity(productDTO);
            product.setProductId(id);
            Product updatedProduct = productRepository.save(product);
            return productConverter.entityToDto(updatedProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
} 