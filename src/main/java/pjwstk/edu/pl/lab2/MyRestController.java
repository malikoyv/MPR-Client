package pjwstk.edu.pl.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {
    @Autowired
    private MyRestService myRestService;

    @GetMapping("/getAllCats")
    public List<Cat> getAllCats(){
        return myRestService.cats;
    }

    @PostMapping("/addCat")
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat){
        myRestService.cats.add(cat);
        return new ResponseEntity<>(cat, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCat/{name}")
    public ResponseEntity<Cat> deleteCat(@PathVariable String name){
        List<Cat> allcats = myRestService.cats;
        for (Cat cat : allcats) {
            if (cat.getName().equals(name)) {
                myRestService.cats.remove(cat);
                return new ResponseEntity<>(cat, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getByAge/{age}")
    public ResponseEntity<List<Cat>> getByAge(@PathVariable int age){
        List<Cat> cats = myRestService.cats;
        List<Cat> result = new ArrayList<>();
        for (Cat cat : cats) {
            if (cat.getAge() == age) {
                result.add(cat);
            }
        }
        if (result.isEmpty()) return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/changeName/{oldName}/{newName}")
    public ResponseEntity<Cat> changeName(@PathVariable String oldName, @PathVariable String newName){
        List<Cat> allcats = myRestService.cats;
        for (Cat cat : allcats) {
            if (cat.getName().equals(oldName)) {
                if (!cat.getName().equals(newName)) {
                    cat.setName(newName);
                    return new ResponseEntity<>(cat, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
