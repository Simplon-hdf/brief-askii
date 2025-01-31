package co.Simplon.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class ProductDTO {

    @Size(max = 50)
    @NotNull(message="L'identifiant ne doit pas être nul.")
    private String productId;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String price;

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

}
