package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.model.Cart;
import workshop.toy.model.CartDetail;
import workshop.toy.model.Toy;
import workshop.toy.repo.CartDetailRepo;
import workshop.toy.repo.CartRepo;
import workshop.toy.repo.ToyRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest")
public class CartController {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ToyRepo toyRepo;

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
        List<CartDetail> cartDetailList = cartDetailRepo.findCartDetailByCartId(id);

        for(int i = 0; i < cartDetailList.size(); i++) {
            CartDetail cartDetail = cartDetailList.get(i);
            if(cartDetail.getDetailPrice() == null) {
                calculateCartDetailPrice(cartDetail);
            }
        }

        return cartDetailList;
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
        Cart cart = cartRepo.findById(id).get();

        if(cart.getTotal() == null) {
            calculateCartPrice(cart);
        }

        return cart;
    }

    @PutMapping("/cart/{id}/address")
    @ResponseBody
    public Cart updateCartAddress(@PathVariable("id")BigDecimal id, @RequestBody Cart newCart) {
        Cart currentCart = cartRepo.findById(id).get();
        currentCart.setShoppingName(newCart.getShoppingName());
        currentCart.setAddr1(newCart.getAddr1());
        currentCart.setAddr2(newCart.getAddr2());
        currentCart.setCity(newCart.getCity());
        currentCart.setProvince(newCart.getProvince());
        currentCart.setPostcode(newCart.getPostcode());
        return cartRepo.save(currentCart);
    }

    @PutMapping("/cart/{id}/price")
    @ResponseBody
    public Cart updateCartPrice(@PathVariable("id")BigDecimal id, @RequestBody Cart newCart) {
        Cart currentCart = cartRepo.findById(id).get();
        currentCart.setSubTotal(newCart.getSubTotal());
        currentCart.setShoppingFee(newCart.getShoppingFee());
        currentCart.setTotal(newCart.getTotal());
        return cartRepo.save(currentCart);
    }

    @PutMapping("/cart/detail")
    @ResponseBody
    public CartDetail addToCart(@RequestBody CartDetail cartDetail) {
        return cartDetailRepo.save(cartDetail);
    }

    public void calculateCartDetailPrice(CartDetail cartDetail) {
        Toy toy = toyRepo.getToyById(cartDetail.getToyId());
        cartDetail.setDetailPrice(toy.getPrice().multiply(cartDetail.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    public void calculateCartPrice(Cart cart) {
        List<CartDetail> cartDetailList = cartDetailRepo.findCartDetailByCartId(cart.getCartId());
        BigDecimal subTotal = new BigDecimal("0");
        BigDecimal shoppingFee = new BigDecimal("50");

        for(int i = 0; i < cartDetailList.size(); i++) {
            CartDetail cartDetail = cartDetailList.get(i);
            if (cartDetail.getDetailPrice() == null) {
                calculateCartDetailPrice(cartDetail);
            }
            subTotal = subTotal.add(cartDetail.getDetailPrice());
        }

        cart.setSubTotal(subTotal);
        cart.setShoppingFee(shoppingFee);
        cart.setTotal(subTotal.add(shoppingFee));
    }
}

