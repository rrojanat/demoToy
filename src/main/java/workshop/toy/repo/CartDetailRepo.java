package workshop.toy.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import workshop.toy.model.CartDetail;
import workshop.toy.model.CartDetailWithToy;
import workshop.toy.model.Combo;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartDetailRepo extends CrudRepository<CartDetail,BigDecimal>{
    @Query("select cd.cartDetailId, cd.cartId, cd.toyId, cd.qty, cd.detailPrice, t.name, g.description as gender, a.description as age, case when t.qty = 0 then 'Out of Stock' else 'In Stock' end stockStatus, t.brand, t.qty as stockQty from CartDetail cd, Toy t, Gender g, Age a where cd.toyId = t.toyId and t.genderId = g.genderId and t.ageId = a.ageId and cd.cartId=:cartId")
    List<CartDetailWithToy> findCartDetailByCartId(@Param("cartId") BigDecimal cart_id);
}
