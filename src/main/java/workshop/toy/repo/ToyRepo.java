package workshop.toy.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import workshop.toy.model.Toy;

import java.math.BigDecimal;
import java.util.List;

public interface ToyRepo extends CrudRepository<Toy,BigDecimal> {

    @Query("select a.toyId, a.name, b.description as gender, c.description as age, a.price, a.brand, a.qty, case when a.qty = 0 then 'Out of Stock' else 'In Stock' end stockStatus, 'Cash on Delivery' as shippingMethod, a.toyImg from Toy a, Gender b, Age c where a.genderId = b.genderId and a.ageId = c.ageId and a.toyId = :toyId")
    Toy getToyById(@Param("toyId") BigDecimal toyId);

    @Query("select a.toyId, a.name, b.description as gender, c.description as age, a.price, a.brand, a.qty, case when a.qty = 0 then 'Out of Stock' else 'In Stock' end stockStatus, 'Cash on Delivery' as shippingMethod, a.toyImg from Toy a, Gender b, Age c where a.genderId = b.genderId and a.ageId = c.ageId")
    List<Toy> searchToy();

    @Query("select a.toyId, a.name, b.description as gender, c.description as age, a.price, a.brand, a.qty, case when a.qty = 0 then 'Out of Stock' else 'In Stock' end stockStatus, 'Cash on Delivery' as shippingMethod, a.toyImg from Toy a, Gender b, Age c where a.genderId = b.genderId and a.ageId = c.ageId and a.ageId = :ageId and a.genderId = :genderId")
    List<Toy> searchToyByAgeAndGender(@Param("ageId") String ageId, @Param("genderId") String genderId);

    @Query("select a.toyId, a.name, b.description as gender, c.description as age, a.price, a.brand, a.qty, case when a.qty = 0 then 'Out of Stock' else 'In Stock' end stockStatus, 'Cash on Delivery' as shippingMethod, a.toyImg from Toy a, Gender b, Age c where a.genderId = b.genderId and a.ageId = c.ageId and a.genderId = :genderId")
    List<Toy> searchToyByGender(@Param("genderId") String genderId);

    @Query("select a.toyId, a.name, b.description as gender, c.description as age, a.price, a.brand, a.qty, case when a.qty = 0 then 'Out of Stock' else 'In Stock' end stockStatus, 'Cash on Delivery' as shippingMethod, a.toyImg from Toy a, Gender b, Age c where a.genderId = b.genderId and a.ageId = c.ageId and a.ageId = :ageId")
    List<Toy> searchToyByAge(@Param("ageId") String ageId);
}
