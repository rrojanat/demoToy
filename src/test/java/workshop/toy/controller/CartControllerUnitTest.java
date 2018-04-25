package workshop.toy.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import workshop.toy.model.CartDetail;
import workshop.toy.repo.CartDetailRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerUnitTest {

    @Mock
    private CartDetailRepo cartDetailRepo;

    private CartController cartController;

    @Before
    public void setup() throws Exception{
        initMocks(this);
        cartController= new CartController(cartDetailRepo);
    }
    @Test
    public void testCartDetailEmpty() throws Exception {
        List<CartDetail> expected = new ArrayList<CartDetail>();
        given(cartDetailRepo.findCartDetailByCartId(new BigDecimal(1)))
                .willReturn(expected);
        List<CartDetail> cartDetailList = cartController.getCartDetailByCartId(new BigDecimal(1));
        assertEquals(expected.size(), cartDetailList.size());
    }
}
