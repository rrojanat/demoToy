package workshop.toy.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.model.CartDetail;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCartDetailEmpty() throws Exception {
        //ResponseEntity<List<CartDetail>> response = restTemplate.getForEntity("/cart/1/detail",  new ParameterizedTypeReference<List<CartDetail>>);
        //assertEquals(200, response.getStatusCode().value());
        //assertEquals(0, response.getBody().size());
    }
}