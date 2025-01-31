package co.Simplon.Repository;

import co.Simplon.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, String> {

    boolean existsByCartIdIgnoreCase(String cartId);

}
