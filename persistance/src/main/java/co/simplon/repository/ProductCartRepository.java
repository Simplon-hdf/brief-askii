package co.simplon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.simplon.entity.Cart;
import co.simplon.entity.Product;
import co.simplon.entity.ProductCart;
import co.simplon.entity.ProductCartId;

import java.util.List;

public interface ProductCartRepository extends JpaRepository<ProductCart, ProductCartId> {

    ProductCart findFirstByCart(Cart cart);

    ProductCart findFirstByProduct(Product product);

    boolean existsByQuantity(Integer quantity);

    @Modifying
    @Query("DELETE FROM ProductCart pc WHERE pc.cart.cartId = :cartId AND pc.product.productId = :productId")
    void deleteByCartAndProductId(@Param("cartId") String cartId, @Param("productId") String productId);

    @Modifying
    @Query("DELETE FROM ProductCart pc WHERE pc.cart.cartId = :cartId")
    void deleteByCartId(@Param("cartId") String cartId);

    @Query("SELECT pc FROM ProductCart pc WHERE pc.cart.cartId = :cartId")
    List<ProductCart> findByCartId(@Param("cartId") String cartId);

}
