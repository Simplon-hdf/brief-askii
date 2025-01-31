package co.Simplon.Repository;

import co.Simplon.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductCartRepository extends JpaRepository<ProductCart, ProductCartId> {

    ProductCart findFirstByCart(Cart cart);

    ProductCart findFirstByProduct(Product product);

    boolean existsByQuantityIgnoreCase(String quantity);

}
