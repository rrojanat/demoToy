package workshop.toy.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.model.Combo;
import workshop.toy.repo.AgeRepo;
import workshop.toy.repo.GenderRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ComboControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private AgeRepo ageRepo;

    @MockBean
    private GenderRepo genderRepo;


    @Test
    public void testGetAgeComboSuccess() throws Exception {
        List<Combo> combos = new ArrayList<>();
        combos.add(new Combo("1", "Baby"));
        combos.add(new Combo("2", "Toddler"));
        given(ageRepo.searchAgeCombo())
                .willReturn(combos);
        ResponseEntity<List> response = restTemplate.getForEntity("/rest/agecombo", List.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void testGetGenderComboSuccess() {
        List<Combo> combos = new ArrayList<>();
        combos.add(new Combo("1", "Female"));
        combos.add(new Combo("2", "Male"));
        given(genderRepo.searchGenderCombo())
                .willReturn(combos);
        ResponseEntity<List> response = restTemplate.getForEntity("/rest/gendercombo", List.class);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
    }
}