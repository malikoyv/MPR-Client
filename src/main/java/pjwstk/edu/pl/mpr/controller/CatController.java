package pjwstk.edu.pl.mpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pjwstk.edu.pl.mpr.exception.CatIsNullException;
import pjwstk.edu.pl.mpr.exception.CatNotFoundException;
import pjwstk.edu.pl.mpr.exception.EmptyString;
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

    @GetMapping("/getAllCats")
    public ResponseEntity<List<Cat>> getAllCats(){
        return new ResponseEntity<>(catService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Cat>> getByName(@PathVariable String name){
        return new ResponseEntity<>(catService.getByName(name), HttpStatus.OK);
    }

    @PostMapping("/addCat")
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat){
        return new ResponseEntity<>(catService.addCat(cat), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCat/{name}")
    public ResponseEntity<List<Cat>> deleteCat(@PathVariable String name){
        return new ResponseEntity<>(catService.deleteByName(name), HttpStatus.OK);
    }

    @GetMapping("/getByAge/{age}")
    public ResponseEntity<List<Cat>> getByAge(@PathVariable int age){
        return new ResponseEntity<>(catService.getByAge(age), HttpStatus.OK);
    }

    @PatchMapping("/changeName/{oldName}/{newName}")
    public ResponseEntity<List<Cat>> changeName(@PathVariable String oldName, @PathVariable String newName){
        return new ResponseEntity<>(catService.changeName(oldName, newName), HttpStatus.OK);
    }

    @PostMapping("/addCatWithUpperName/{name}/{age}")
    public ResponseEntity<Cat> addCatUppercase(@PathVariable String name, @PathVariable int age) {
        return new ResponseEntity<>(catService.addCatWithUpperName(name, age), HttpStatus.OK);
    }

    @GetMapping("/getCatsLower/{name}")
    public ResponseEntity<List<Cat>> getCatsLower(@PathVariable String name) {
        return new ResponseEntity<>(catService.changeAllUpperToLowerByName(name), HttpStatus.OK);
    }
}
