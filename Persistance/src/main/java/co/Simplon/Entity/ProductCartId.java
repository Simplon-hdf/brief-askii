package co.Simplon.Entity;

import java.io.Serializable;
import java.util.Objects;

public class ProductCartId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String cart;    // Doit correspondre à l'ID de Cart
	private String product; // Doit correspondre à l'ID de Product

	public ProductCartId() {
		// Constructeur par défaut requis par JPA
	}

	public ProductCartId(String cart, String product) {
		this.cart = cart;
		this.product = product;
	}

	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ProductCartId that = (ProductCartId) o;
		return Objects.equals(cart, that.cart) && 
			   Objects.equals(product, that.product);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cart, product);
	}
}
