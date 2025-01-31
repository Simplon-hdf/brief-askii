package co.Simplon.Repository;

import co.Simplon.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, String> {

    boolean existsByProductIdIgnoreCase(String productId);

}
