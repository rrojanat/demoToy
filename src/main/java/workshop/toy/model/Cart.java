package workshop.toy.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

public class Cart {
    @Id
    private BigDecimal cartId;
    private BigDecimal subTotal;
    private BigDecimal shoppingFee;
    private BigDecimal total;
    private String shoppingName;
    private String addr1;
    private String addr2;
    private String city;
    private String province;
    private String postcode;
    private String email;

    public Cart() {
    }

    public BigDecimal getCartId() {
        return cartId;
    }

    public void setCartId(BigDecimal cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getShoppingFee() {
        return shoppingFee;
    }

    public void setShoppingFee(BigDecimal shopping_fee) {
        this.shoppingFee = shopping_fee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getShoppingName() {
        return shoppingName;
    }

    public void setShoppingName(String shoppingName) {
        this.shoppingName = shoppingName;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
