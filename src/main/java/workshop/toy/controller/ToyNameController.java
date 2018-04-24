package workshop.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import workshop.toy.model.Toy;
import workshop.toy.repo.ToyRepo;

import java.math.BigDecimal;

@RestController
public class ToyNameController {
    @Autowired
    private ToyRepo toyRepo;

    @GetMapping("/rest/getToyById/{toyId}")
    @ResponseBody
    public Toy getToy(@PathVariable String toyId) {
        return toyRepo.getToyById(new BigDecimal(toyId));
    }

}
