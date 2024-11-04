package pjwstk.edu.pl.mpr.controller;

import org.antlr.v4.runtime.atn.SemanticContext;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pjwstk.edu.pl.mpr.exception.CatIsNullException;
import pjwstk.edu.pl.mpr.exception.CatNotFoundException;
import pjwstk.edu.pl.mpr.exception.EmptyString;
import pjwstk.edu.pl.mpr.model.Cat;
import pjwstk.edu.pl.mpr.repository.CatRepository;
import pjwstk.edu.pl.mpr.service.MyRestService;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    @Autowired
    private CatRepository repository;

    @Autowired
    private MyRestService myRestService = new MyRestService(repository);

    @GetMapping("/getAllCats")
    public ResponseEntity<Iterable<Cat>> getAllCats(){
        Iterable<Cat> cats = repository.findAll();

        return new ResponseEntity<>(myRestService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Cat>> getByName(@PathVariable String name){
        List<Cat> cats = myRestService.getByName(name);
        if (cats == null || cats.isEmpty()){
            throw new CatNotFoundException("Cat not found");
        }

        return new ResponseEntity<>(myRestService.getByName(name), HttpStatus.OK);
    }

    @PostMapping("/addCat")
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat){
        if (cat == null){
            throw new CatIsNullException("Cat's body is null");
        }

        return new ResponseEntity<>(myRestService.addCat(cat), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCat/{name}")
    public ResponseEntity<List<Cat>> deleteCat(@PathVariable String name){
        List<Cat> cats = myRestService.deleteByName(name);
        if (cats == null || cats.isEmpty()){
            throw new CatNotFoundException("Cat not found");
        }

        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @GetMapping("/getByAge/{age}")
    public ResponseEntity<List<Cat>> getByAge(@PathVariable int age){
        List<Cat> cats = myRestService.getByAge(age);
        if (!cats.isEmpty()) {
            throw new CatNotFoundException("Cat not found");
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/changeName/{oldName}/{newName}")
    public ResponseEntity<List<Cat>> changeName(@PathVariable String oldName, @PathVariable String newName){
        List<Cat> cats = myRestService.changeName(oldName, newName);
        if (oldName.isEmpty() || newName.isEmpty()){
            throw new EmptyString("Your name is empty. Please write cat's name!");
        }

        if (cats == null || cats.isEmpty()){
            throw new CatNotFoundException("Cat not found");
        }

        return new ResponseEntity<>(cats, HttpStatus.OK);
    }
}
