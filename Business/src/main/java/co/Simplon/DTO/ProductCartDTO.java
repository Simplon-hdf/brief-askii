package co.Simplon.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ProductCartDTO {

	@Size(max = 50)
    private String quantity;

    @Size(max = 50)
    @NotNull(message="Le panier ne doit pas être nul.")
    private String cart;

    @Size(max = 50)
    @NotNull(message="Le produit ne doit pas être nul.")
    private String product;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(final String quantity) {
        this.quantity = quantity;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(final String cart) {
        this.cart = cart;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(final String product) {
        this.product = product;
    }

}
