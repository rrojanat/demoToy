package workshop.toy.repo;

import org.springframework.data.repository.CrudRepository;
import workshop.toy.model.Combo;
import org.springframework.data.jdbc.repository.query.Query;

import java.util.List;

public interface GenderRepo extends CrudRepository<Combo,Integer> {
    @Query("select gender_id as value , description as text from gender order by gender_id")
    List<Combo> searchGenderCombo();
}
