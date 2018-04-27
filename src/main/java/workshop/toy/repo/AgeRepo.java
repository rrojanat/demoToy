package workshop.toy.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import workshop.toy.model.Combo;

import java.util.List;

public interface AgeRepo extends CrudRepository<Combo,Integer> {

    @Query("select ageId as value , description as text from Age order by ageId")
    List<Combo> searchAgeCombo();
}
