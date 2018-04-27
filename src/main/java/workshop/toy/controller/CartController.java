package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.model.Cart;
import workshop.toy.model.CartDetail;
import workshop.toy.model.CartDetailWithToy;
import workshop.toy.model.Toy;
import workshop.toy.repo.CartDetailRepo;
import workshop.toy.repo.CartRepo;
import workshop.toy.repo.ToyRepo;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/rest")
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

    @GetMapping(value = "/cart/{id}/detail", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CartDetailWithToy> getCartDetailByCartId(@PathVariable("id")BigDecimal id){
        List<CartDetailWithToy> cartDetailList = cartDetailRepo.findCartDetailByCartId(id);

        for(int i = 0; i < cartDetailList.size(); i++) {
            CartDetailWithToy cartDetail = cartDetailList.get(i);
            if(cartDetail.getDetailPrice() == null) {
                calculateCartDetailPrice(cartDetail);
            }
        }

        return cartDetailList;
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
    public Cart updateCartPrice(@PathVariable("id")BigDecimal id) {
        Cart cart = cartRepo.findById(id).get();
        calculateCartPrice(cart);

        List<CartDetailWithToy> cartDetailList = cartDetailRepo.findCartDetailByCartId(id);

        for(int i = 0; i < cartDetailList.size(); i++) {
            CartDetailWithToy cartDetail = cartDetailList.get(i);
            calculateCartDetailPrice(cartDetail);
            CartDetail updateCartDetail = cartDetailRepo.findById(cartDetail.getCartDetailId()).get();
            updateCartDetail.setDetailPrice(cartDetail.getDetailPrice());
            cartDetailRepo.save(updateCartDetail);
        }

        return cartRepo.save(cart);
    }

    @PutMapping("/cart/detail")
    @ResponseBody
    public CartDetail addToCart(@RequestBody CartDetail cartDetail) {
        return cartDetailRepo.save(cartDetail);
    }

    @GetMapping(value = "/cart/detail/{id}", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public CartDetail getCartDetailByCartDetailId(@PathVariable("id")BigDecimal id) {
        return cartDetailRepo.findById(id).get();
    }

    @DeleteMapping ("/cart/detail/{id}")
    @ResponseBody
    public CartDetail deleteCartDetail(@PathVariable("id")BigDecimal id) {
        CartDetail cartDetail = cartDetailRepo.findById(id).get();
        cartDetailRepo.delete(cartDetail);
        return cartDetail;
    }

    @PutMapping("/cart/detail/{id}/qty")
    @ResponseBody
    public CartDetail updateCartDetailQty(@PathVariable("id")BigDecimal id, @RequestBody CartDetail newCartDetail) {
        CartDetail currentCartDetail = cartDetailRepo.findById(id).get();
        currentCartDetail.setQty(newCartDetail.getQty());
        return cartDetailRepo.save(currentCartDetail);
    }

    private void calculateCartDetailPrice(CartDetailWithToy cartDetail) {
        Toy toy = toyRepo.getToyById(cartDetail.getToyId());
        cartDetail.setDetailPrice(toy.getPrice().multiply(cartDetail.getQty()).setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private void calculateCartPrice(Cart cart) {
        List<CartDetailWithToy> cartDetailList = cartDetailRepo.findCartDetailByCartId(cart.getCartId());
        BigDecimal subTotal = new BigDecimal("0");
        BigDecimal shoppingFee = new BigDecimal("50");

        for(int i = 0; i < cartDetailList.size(); i++) {
            CartDetailWithToy cartDetail = cartDetailList.get(i);
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

