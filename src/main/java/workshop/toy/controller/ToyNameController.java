package workshop.toy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import workshop.toy.model.Toy;

@RestController
public class ToyNameController {

    @GetMapping("/getToyById/{toyId}")
    @ResponseBody
    public Toy getToy(@PathVariable String toyId) {
        Toy toy = new Toy();
        toy.setToyName("Test Name");
        toy.setBrand("Test Brand");
        toy.setGender("Neutral");
        toy.setAge("Baby");
        toy.setPrice("10.15");
        toy.setShippingMethod("Cash on Delivery");
        toy.setStockStatus("In Stock");
        return toy;
    }

}
