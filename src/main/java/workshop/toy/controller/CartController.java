package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.model.Cart;
import workshop.toy.model.CartDetail;
import workshop.toy.repo.CartDetailRepo;
import workshop.toy.repo.CartRepo;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("rest")
public class CartController {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartDetailRepo cartDetailRepo;

    @GetMapping(value = "/cart/{id}/detail" ,produces = "application/json; charset=UTF-8")
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

    @PutMapping("/cart/detail")
    @ResponseBody
    public CartDetail addToCart(@RequestBody CartDetail cartDetail) {
        return cartDetailRepo.save(cartDetail);
    }
}

