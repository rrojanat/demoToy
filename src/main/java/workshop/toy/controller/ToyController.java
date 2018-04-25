package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.model.Toy;
import workshop.toy.model.ToyCriteria;
import workshop.toy.repo.ToyRepo;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ToyController {
    @Autowired
    private ToyRepo toyRepo;

    @GetMapping("/rest/toy/{toyId}")
    @ResponseBody
    public Toy getToy(@PathVariable String toyId) {
        return toyRepo.getToyById(new BigDecimal(toyId));
    }

    @PostMapping("/rest/toy")
    @ResponseBody
    public List<Toy> searchToy(@RequestBody ToyCriteria toyCriteria) {
        String ageId = toyCriteria.getSearchAge();
        String genderId = toyCriteria.getSearchGender();
        if (isEmptyString(ageId) && isEmptyString(genderId)) {
            return toyRepo.searchToyByAgeAndGender(ageId, genderId);
        } else if (isEmptyString(ageId) && !isEmptyString(genderId)) {
            return toyRepo.searchToyByGender(genderId);
        } else if (!isEmptyString(ageId) && isEmptyString(genderId)) {
            return toyRepo.searchToyByAge(ageId);
        }

        return toyRepo.searchToy();
    }

    private boolean isEmptyString(String input) {
        if (input == null || input.equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }
}
