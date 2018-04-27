package workshop.toy.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import workshop.toy.model.Cart;

import java.math.BigDecimal;

public interface CartRepo extends CrudRepository<Cart,BigDecimal> {
}
