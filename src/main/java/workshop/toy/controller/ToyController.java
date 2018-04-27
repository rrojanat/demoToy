package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.model.Toy;
import workshop.toy.model.ToyCriteria;
import workshop.toy.repo.ToyRepo;

import java.math.BigDecimal;
import java.util.List;

@RestController()
@RequestMapping("/rest")
public class ToyController {
    @Autowired
    private ToyRepo toyRepo;

    @GetMapping("/toy/{toyId}")
    @ResponseBody
    public Toy getToy(@PathVariable String toyId) {
        return toyRepo.getToyById(new BigDecimal(toyId));
    }

    @PostMapping("/toy")
    @ResponseBody
    public List<Toy> searchToy(@RequestBody ToyCriteria toyCriteria) {
        String ageId = toyCriteria.getSearchAge();
        String genderId = toyCriteria.getSearchGender();
        if (isSearchByAgeAndGender(ageId, genderId)) {
            return toyRepo.searchToyByAgeAndGender(ageId, genderId);
        } else if (isSearchByGender(ageId, genderId)) {
            return toyRepo.searchToyByGender(genderId);
        } else if (isSearchByAge(ageId, genderId)) {
            return toyRepo.searchToyByAge(ageId);
        }

        return toyRepo.searchToy();
    }

    private boolean isSearchByAgeAndGender(String ageId, String genderId) {
        return !isEmptyString(ageId) && !isEmptyString(genderId);
    }

    private boolean isSearchByGender(String ageId, String genderId) {
        return isEmptyString(ageId) && !isEmptyString(genderId);
    }

    private boolean isSearchByAge(String ageId, String genderId) {
        return !isEmptyString(ageId) && isEmptyString(genderId);
    }

    private boolean isEmptyString(String input) {
        return input == null || input.equalsIgnoreCase("");
    }
}
