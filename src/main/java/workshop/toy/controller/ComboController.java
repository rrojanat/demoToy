package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import workshop.toy.model.Combo;
import workshop.toy.repo.AgeRepo;

import java.util.List;

@RestController
public class ComboController {
    @Autowired
    private AgeRepo ageRepo;

    @GetMapping("/rest/getAgeCombo")
    public List<Combo> getAgeCombo(){
        return ageRepo.searchAgeCombo();
    }
}
