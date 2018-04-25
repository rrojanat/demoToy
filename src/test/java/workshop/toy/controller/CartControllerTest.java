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
import workshop.toy.model.CartDetail;
import workshop.toy.model.Combo;
import workshop.toy.repo.CartDetailRepo;
import workshop.toy.repo.GenderRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CartDetailRepo cartDetailRepo;

    @Test
    public void testCartDetailEmpty() throws Exception {
        List<CartDetail> cartDats = new ArrayList<CartDetail>();
        given(cartDetailRepo.findCartDetailByCartId(new BigDecimal(1)))
                .willReturn(cartDats);
        ResponseEntity<List> response = restTemplate.getForEntity("/rest/cart/1/detail", List.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(0, response.getBody().size());
    }
}