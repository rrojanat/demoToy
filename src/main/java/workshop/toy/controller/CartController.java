package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.model.Cart;
import workshop.toy.model.CartDetail;
import workshop.toy.repo.CartDetailRepo;
import workshop.toy.repo.CartRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest")
public class CartController {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartDetailRepo cartDetailRepo;

    public CartController() {
    }

    public CartController(CartDetailRepo cartDetailRepo) {
        this.cartDetailRepo = cartDetailRepo;
    }

    public CartController(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @GetMapping(value = "/cart/{id}/detail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CartDetail> getCartDetailByCartId(@PathVariable("id")BigDecimal id){
        return cartDetailRepo.findCartDetailByCartId(id);
    }

    @PutMapping("/cart")
    @ResponseBody
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepo.save(cart);
    }

    @GetMapping(value = "/cart/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Cart getCartByCartId(@PathVariable("id")BigDecimal id) {
        Optional<Cart> optionalCart = cartRepo.findById(id);
        return optionalCart.isPresent() ? optionalCart.get() : null;
    }

    @PutMapping("/cart/{id}")
    @ResponseBody
    public Cart updateCart(@PathVariable("id")BigDecimal id, @RequestBody Cart newCart) {
        Cart currentCart = cartRepo.findById(id).get();
        currentCart.setShoppingName(newCart.getShoppingName());
        currentCart.setAddr1(newCart.getAddr1());
        currentCart.setAddr2(newCart.getAddr2());
        currentCart.setCity(newCart.getCity());
        currentCart.setProvince(newCart.getProvince());
        currentCart.setPostcode(newCart.getPostcode());
        return cartRepo.save(currentCart);
    }

    @PutMapping("/cart/detail")
    @ResponseBody
    public CartDetail addToCart(@RequestBody CartDetail cartDetail) {
        return cartDetailRepo.save(cartDetail);
    }
}

