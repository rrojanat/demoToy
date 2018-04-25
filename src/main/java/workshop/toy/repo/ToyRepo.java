package workshop.toy.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import workshop.toy.model.Toy;

import java.math.BigDecimal;

public interface ToyRepo extends CrudRepository<Toy,Integer> {

    @Query("select a.toy_id, a.name, b.description as gender, c.description as age, a.price, a.brand, a.qty, case when a.qty = 0 then 'Out of Stock' else 'In Stock' end stockStatus, 'Cash on Delivery' as shippingMethod, a.toy_img from toy a, gender b, age c where a.gender_id = b.gender_id and a.age_id = c.age_id and a.toy_id = :toyId")
    Toy getToyById(@Param("toyId") BigDecimal toyId);
}
