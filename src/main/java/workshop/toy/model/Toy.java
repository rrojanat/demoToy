package workshop.toy.model;

import java.math.BigDecimal;

public class Toy {
    private BigDecimal toy_id;
    private String name;
    private String brand;
    private String gender;
    private String age;
    private BigDecimal price;
    private String shippingMethod;
    private String stockStatus;
    private BigDecimal qty;
    private String toy_img;

    public BigDecimal getToyId() {
        return toy_id;
    }

    public void setToyId(BigDecimal toyId) {
        this.toy_id = toyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String toyName) {
        this.name = toyName;
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
        return toy_img;
    }

    public void setToyImg(String toyImg) {
        this.toy_img = toyImg;
    }
}
