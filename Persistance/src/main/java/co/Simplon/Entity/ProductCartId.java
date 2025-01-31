package co.Simplon.Entity;

import java.io.Serializable;

public class ProductCartId implements Serializable {
	private Cart cart;
	public Cart getCart() {
		return cart;
	}

	public void setCart(final Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(final Product product) {
		this.product = product;
	}

	private Product product;
	
	public ProductCartId(Cart cart, Product product) {
		this.cart = cart;
		this.product = product;
	}
}
