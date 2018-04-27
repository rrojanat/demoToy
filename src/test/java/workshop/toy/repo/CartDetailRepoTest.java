package workshop.toy.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.model.Cart;
import workshop.toy.model.CartDetail;
import workshop.toy.model.CartDetailWithToy;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
public class CartDetailRepoTest {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartDetailRepo cartDetailRepo;

    @Test
    public void testAddNewCart(){
        cartRepo.save(new Cart());
        CartDetail cartDetail = new CartDetail();
        cartDetail.setToyId(new BigDecimal("1"));
        cartDetail.setCartId(new BigDecimal("2"));
        cartDetail.setQty(new BigDecimal("1"));
        cartDetailRepo.save(cartDetail);

        List<CartDetailWithToy> cartDetailList = cartDetailRepo.findCartDetailByCartId(new BigDecimal("2"));
        assertEquals(1, cartDetailList.size());
    }

    @Test
    public void testAddAndRemoveFromExistingCart(){
        CartDetail addCartDetail = new CartDetail();
        addCartDetail.setToyId(new BigDecimal("1"));
        addCartDetail.setCartId(new BigDecimal("1"));
        addCartDetail.setQty(new BigDecimal("1"));
        cartDetailRepo.save(addCartDetail);

        CartDetail deleteCartDetail = new CartDetail();
        deleteCartDetail.setCartDetailId(new BigDecimal("1"));
        cartDetailRepo.delete(deleteCartDetail);

        List<CartDetailWithToy> cartDetailList = cartDetailRepo.findCartDetailByCartId(new BigDecimal("1"));
        assertEquals(3, cartDetailList.size());
    }
}
