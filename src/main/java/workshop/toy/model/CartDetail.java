package workshop.toy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@JsonIgnoreProperties
public class CartDetail {
    @JsonView
    @Id
    private BigDecimal cartDetailId;
    @JsonView
    private BigDecimal cartId;
    @JsonView
    private BigDecimal toyId;
    @JsonView
    private BigDecimal qty;
    @JsonView
    private BigDecimal detailPrice;

    public CartDetail() {
    }

    public CartDetail(BigDecimal cartDetailId, BigDecimal cartId, BigDecimal toyId, BigDecimal qty, BigDecimal detailPrice) {
        this.cartDetailId = cartDetailId;
        this.cartId = cartId;
        this.toyId = toyId;
        this.qty = qty;
        this.detailPrice = detailPrice;
    }

    public BigDecimal getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(BigDecimal cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public BigDecimal getCartId() {
        return cartId;
    }

    public void setCartId(BigDecimal cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getToyId() {
        return toyId;
    }

    public void setToyId(BigDecimal toyId) {
        this.toyId = toyId;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(BigDecimal detailPrice) {
        this.detailPrice = detailPrice;
    }
}
