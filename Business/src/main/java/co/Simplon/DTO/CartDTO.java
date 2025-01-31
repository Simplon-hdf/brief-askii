package co.Simplon.DTO;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.*;


public class CartDTO {

    @Size(max = 50)
    @NotNull(message="L'identifiant ne doit pas être nul.")
    private String cartId;

    @Size(max = 50)
    private String saveDate;

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

}
