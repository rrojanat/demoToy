package workshop.toy.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.model.Cart;
import workshop.toy.model.CartDetail;
import workshop.toy.model.Combo;
import workshop.toy.model.Toy;
import workshop.toy.repo.CartDetailRepo;
import workshop.toy.repo.CartRepo;
import workshop.toy.repo.GenderRepo;
import workshop.toy.repo.ToyRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ToyRepo toyRepo;

    @MockBean
    private CartRepo cartRepo;

    @MockBean
    private CartDetailRepo cartDetailRepo;

    @Test
    public void testCartDetailEmpty() throws Exception {
        List<CartDetail> cartDats = new ArrayList<CartDetail>();
        given(cartDetailRepo.findCartDetailByCartId(new BigDecimal("1")))
                .willReturn(cartDats);
        ResponseEntity<List> response = restTemplate.getForEntity("/rest/cart/1/detail", List.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(0, response.getBody().size());
    }

    @Test
    public void testCalculateCartPrice() throws Exception {
        Cart cart = new Cart();
        cart.setCartId(new BigDecimal("1"));

        List<CartDetail> cartDetailList = new ArrayList<CartDetail>();
        CartDetail cartDetail1 = new CartDetail();
        cartDetail1.setCartDetailId(new BigDecimal("1"));
        cartDetail1.setCartId(new BigDecimal("1"));
        cartDetail1.setToyId(new BigDecimal("1"));
        cartDetail1.setQty(new BigDecimal("2"));

        CartDetail cartDetail2 = new CartDetail();
        cartDetail2.setCartDetailId(new BigDecimal("1"));
        cartDetail2.setCartId(new BigDecimal("1"));
        cartDetail2.setToyId(new BigDecimal("2"));
        cartDetail2.setQty(new BigDecimal("1"));

        cartDetailList.add(cartDetail1);
        cartDetailList.add(cartDetail2);

        Toy toy1 = new Toy();
        toy1.setToyId(new BigDecimal("1"));
        toy1.setPrice(new BigDecimal("11.25"));

        Toy toy2 = new Toy();
        toy2.setToyId(new BigDecimal("2"));
        toy2.setPrice(new BigDecimal("14.75"));

        given(cartRepo.findById(new BigDecimal("1")))
                .willReturn(Optional.of(cart));

        given(cartDetailRepo.findCartDetailByCartId(new BigDecimal("1")))
                .willReturn(cartDetailList);

        given(toyRepo.getToyById(new BigDecimal("1")))
                .willReturn(toy1);

        given(toyRepo.getToyById(new BigDecimal("2")))
                .willReturn(toy2);

        ResponseEntity<Cart> response = restTemplate.getForEntity("/rest/cart/1", Cart.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(new BigDecimal("37.25"), response.getBody().getSubTotal().setScale(2));
        assertEquals(new BigDecimal("50.00"), response.getBody().getShoppingFee().setScale(2));
        assertEquals(new BigDecimal("87.25"), response.getBody().getTotal().setScale(2));
    }

    @Test
    public void testCalculateCartDetailPrice() throws Exception {
        List<CartDetail> cartDetailList = new ArrayList<CartDetail>();
        CartDetail cartDetail1 = new CartDetail();
        cartDetail1.setCartDetailId(new BigDecimal("1"));
        cartDetail1.setCartId(new BigDecimal("1"));
        cartDetail1.setToyId(new BigDecimal("1"));
        cartDetail1.setQty(new BigDecimal("2"));

        CartDetail cartDetail2 = new CartDetail();
        cartDetail2.setCartDetailId(new BigDecimal("1"));
        cartDetail2.setCartId(new BigDecimal("1"));
        cartDetail2.setToyId(new BigDecimal("2"));
        cartDetail2.setQty(new BigDecimal("1"));

        cartDetailList.add(cartDetail1);
        cartDetailList.add(cartDetail2);

        Toy toy1 = new Toy();
        toy1.setToyId(new BigDecimal("1"));
        toy1.setPrice(new BigDecimal("11.25"));

        Toy toy2 = new Toy();
        toy2.setToyId(new BigDecimal("2"));
        toy2.setPrice(new BigDecimal("14.75"));

        given(cartDetailRepo.findCartDetailByCartId(new BigDecimal("1")))
                .willReturn(cartDetailList);

        given(toyRepo.getToyById(new BigDecimal("1")))
                .willReturn(toy1);

        given(toyRepo.getToyById(new BigDecimal("2")))
                .willReturn(toy2);

        ResponseEntity<List> response = restTemplate.getForEntity("/rest/cart/1/detail", List.class);
        assertEquals(200, response.getStatusCode().value());
        List resultCartDetailList = response.getBody();
        assertEquals(new BigDecimal("22.50"), new BigDecimal(((LinkedHashMap) resultCartDetailList.get(0)).get("detailPrice").toString()).setScale(2));
        assertEquals(new BigDecimal("14.75"), new BigDecimal(((LinkedHashMap) resultCartDetailList.get(1)).get("detailPrice").toString()).setScale(2));
    }
}