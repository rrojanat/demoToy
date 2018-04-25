package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import workshop.toy.model.Cart;
import workshop.toy.repo.CartRepo;

import java.math.BigDecimal;

@RestController
public class CartController {
    @Autowired
    private CartRepo cartRepo;

    @PutMapping("/rest/cart")
    @ResponseBody
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepo.save(cart);
    }
}
