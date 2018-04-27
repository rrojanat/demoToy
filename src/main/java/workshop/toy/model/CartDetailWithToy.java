package workshop.toy.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class CartDetailWithToy {
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
    @JsonView
    private String name;
    @JsonView
    private String gender;
    @JsonView
    private String age;
    @JsonView
    private String stockStatus;
    @JsonView
    private String brand;
    @JsonView
    private BigDecimal stockQty;

    public CartDetailWithToy() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getStockQty() {
        return stockQty;
    }

    public void setStockQty(BigDecimal stockQty) {
        this.stockQty = stockQty;
    }
}
