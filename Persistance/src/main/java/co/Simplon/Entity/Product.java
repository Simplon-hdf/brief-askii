package co.Simplon.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;


@Entity
public class Product {

    @Id
    @Column(nullable = false, updatable = false, length = 50)
    private String productId;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String price;

    @OneToMany(mappedBy = "product")
    private Set<ProductCart> productProductCarts;

    public String getProductId() {
        return productId;
    }

    public void setProductId(final String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }

    public Set<ProductCart> getProductProductCarts() {
        return productProductCarts;
    }

    public void setProductProductCarts(final Set<ProductCart> productProductCarts) {
        this.productProductCarts = productProductCarts;
    }

}
