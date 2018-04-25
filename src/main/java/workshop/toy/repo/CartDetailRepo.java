package workshop.toy.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import workshop.toy.model.CartDetail;
import workshop.toy.model.Combo;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartDetailRepo extends CrudRepository<CartDetail,BigDecimal>{
    @Query("select * from CartDetail where cartId=:cartId")
    List<CartDetail> findCartDetailByCartId(@Param("cartId") BigDecimal cart_id);
}
