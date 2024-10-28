package pjwstk.edu.pl.mpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pjwstk.edu.pl.mpr.model.Cat;
import pjwstk.edu.pl.mpr.repository.CatRepository;
import pjwstk.edu.pl.mpr.service.CatService;

import java.util.List;

@Controller
public class CatController {
    @Autowired
    private CatRepository repository;

    @Autowired
    private CatService catService = new CatService(repository);

    @PostMapping("/addCatWithUpperName/{name}/{age}")
    public ResponseEntity<Cat> addCatUppercase(@PathVariable String name, @PathVariable int age) {
        Cat cat = catService.addCatWithUpperName(name, age);

        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @GetMapping("/getCatsLower/{name}")
    public ResponseEntity<List<Cat>> getCatsLower(@PathVariable String name) {
        List<Cat> cats = catService.changeAllUpperToLowerByName(name);

        if (cats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cats, HttpStatus.OK);
    }
}
