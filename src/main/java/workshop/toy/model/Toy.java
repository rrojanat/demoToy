package workshop.toy.model;

import java.math.BigDecimal;

public class Toy {
    private BigDecimal toyId;
    private String toyName;
    private String brand;
    private String gender;
    private String age;
    private BigDecimal price;
    private String shippingMethod;
    private String stockStatus;
    private BigDecimal qty;
    private String toyImg;

    public BigDecimal getToyId() {
        return toyId;
    }

    public void setToyId(BigDecimal toyId) {
        this.toyId = toyId;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getToyImg() {
        return toyImg;
    }

    public void setToyImg(String toyImg) {
        this.toyImg = toyImg;
    }
}
