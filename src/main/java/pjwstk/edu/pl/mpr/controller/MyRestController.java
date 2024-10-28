package pjwstk.edu.pl.mpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjwstk.edu.pl.mpr.model.Cat;
import pjwstk.edu.pl.mpr.repository.CatRepository;
import pjwstk.edu.pl.mpr.service.MyRestService;

import java.util.List;

@RestController
public class MyRestController {
    @Autowired
    private CatRepository repository;

    @Autowired
    private MyRestService myRestService = new MyRestService(repository);

    @GetMapping("/getAllCats")
    public Iterable<Cat> getAllCats(){
        return myRestService.catRepository.findAll();
    }

    @GetMapping("/getByName/{name}")
    public List<Cat> getByName(@PathVariable String name){
        return myRestService.catRepository.findByName(name);
    }

    @PostMapping("/addCat")
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat){
        myRestService.catRepository.save(cat);
        cat.setIdentificator();
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCat/{name}")
    public ResponseEntity<Cat> deleteCat(@PathVariable String name){
        List<Cat> cats = myRestService.catRepository.findByName(name);
        myRestService.catRepository.deleteAll(cats);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/getByAge/{age}")
    public ResponseEntity<List<Cat>> getByAge(@PathVariable int age){
        List<Cat> cats = myRestService.catRepository.findByAge(age);
        if (!cats.isEmpty()) {
            return new ResponseEntity<>(cats, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/changeName/{oldName}/{newName}")
    public ResponseEntity<List<Cat>> changeName(@PathVariable String oldName, @PathVariable String newName){
        List<Cat> cats = myRestService.catRepository.findByName(oldName);
        if (!cats.isEmpty()) {
            for (Cat cat : cats) {
                if (!cat.getName().equals(newName)) {
                    cat.setName(newName);
                    myRestService.catRepository.save(cat);
                    cat.setIdentificator();
                }
            }
            return new ResponseEntity<>(cats, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
