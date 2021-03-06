package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import workshop.toy.model.Combo;
import workshop.toy.repo.AgeRepo;
import workshop.toy.repo.GenderRepo;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ComboController {
    @Autowired
    private AgeRepo ageRepo;
    @Autowired
    private GenderRepo genderRepo;

    @GetMapping("/agecombo")
    public List<Combo> getAgeCombo(){
        return ageRepo.searchAgeCombo();
    }

    @GetMapping("/gendercombo")
    public List<Combo> getGenderCombo(){
        return genderRepo.searchGenderCombo();
    }
}
