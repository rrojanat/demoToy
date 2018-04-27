package workshop.toy.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.model.Toy;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@JdbcTest
public class ToyRepoTest {

    @Autowired
    private ToyRepo toyRepo;

    @Test
    public void testGetToyById(){
        Toy toyResult = toyRepo.getToyById(new BigDecimal(1));
        assertEquals(new BigDecimal("1"), toyResult.getToyId());
        assertEquals("Balance Training Bicycle", toyResult.getName());
        assertEquals("Neutral", toyResult.getGender());
        assertEquals("3_to_5", toyResult.getAge());
        assertEquals(new BigDecimal("119.95"), toyResult.getPrice());
        assertEquals("SportsFun", toyResult.getBrand());
        assertEquals(new BigDecimal("20"), toyResult.getQty());
        assertEquals("/img/1.jpg", toyResult.getToyImg());
    }

    @Test
    public void testSearchToy(){
        List<Toy> toyListResult = toyRepo.searchToy();
        assertEquals(3, toyListResult.size());
    }

    @Test
    public void testSearchToyByAgeAndGender(){
        List<Toy> toyListResult = toyRepo.searchToyByAgeAndGender("2","1");
        assertEquals(1, toyListResult.size());
    }

    @Test
    public void testSearchToyByAge(){
        List<Toy> toyListResult = toyRepo.searchToyByAge("2");
        assertEquals(2, toyListResult.size());
    }

    @Test
    public void testSearchToyByGender(){
        List<Toy> toyListResult = toyRepo.searchToyByGender("3");
        assertEquals(2, toyListResult.size());
    }
}
