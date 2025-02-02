package co.simplon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;


@Entity
public class Cart {

    @Id
    @Column(nullable = false, updatable = false, length = 50)
    private String cartId;

    @Column(length = 50)
    private String saveDate;

    @OneToMany(mappedBy = "cart")
    private Set<ProductCart> cartProductCarts;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(final String cartId) {
        this.cartId = cartId;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(final String saveDate) {
        this.saveDate = saveDate;
    }

    public Set<ProductCart> getCartProductCarts() {
        return cartProductCarts;
    }

    public void setCartProductCarts(final Set<ProductCart> cartProductCarts) {
        this.cartProductCarts = cartProductCarts;
    }

}
