package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.model.CartDetail;
import workshop.toy.model.Combo;
import workshop.toy.repo.CartDetailRepo;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("rest")
public class CartController {

    @Autowired
    CartDetailRepo cartDetailRepo;

    @GetMapping(value = "/cart/{id}/detail" ,produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CartDetail> getCartDetailByCartId(@PathVariable("id")BigDecimal id){
        return cartDetailRepo.findCartDetailByCartId(id);
    }
}


