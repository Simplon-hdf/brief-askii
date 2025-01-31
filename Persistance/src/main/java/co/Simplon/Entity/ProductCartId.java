package co.Simplon.Entity;

import java.io.Serializable;

public class ProductCartId implements Serializable {
	private Cart cart;
	private Product product;
	
	public ProductCartId(Cart cart, Product product) {
		this.cart = cart;
		this.product = product;
	}
}
