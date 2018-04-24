package workshop.toy.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import workshop.toy.model.Combo;

import java.util.List;

public interface AgeRepo extends CrudRepository<Combo,Integer> {

    @Query("select age_id as value , description as text from age")
    List<Combo> searchAgeCombo();
}
