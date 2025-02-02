package co.simplon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.entity.Cart;


public interface CartRepository extends JpaRepository<Cart, String> {

    boolean existsByCartIdIgnoreCase(String cartId);

}
