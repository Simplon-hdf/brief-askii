package co.simplon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.entity.Product;


public interface ProductRepository extends JpaRepository<Product, String> {

    boolean existsByProductIdIgnoreCase(String productId);

}
