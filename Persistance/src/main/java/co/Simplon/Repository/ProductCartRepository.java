package co.Simplon.Repository;

import co.Simplon.Entity.Cart;
import co.Simplon.Entity.Product;
import co.Simplon.Entity.ProductCart;
import co.Simplon.Entity.ProductCartId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductCartRepository extends JpaRepository<ProductCart, ProductCartId> {

    ProductCart findFirstByCart(Cart cart);

    ProductCart findFirstByProduct(Product product);

    boolean existsByQuantityIgnoreCase(String quantity);

}
