package pjwstk.edu.pl.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjwstk.edu.pl.lab2.model.Cat;
import pjwstk.edu.pl.lab2.repository.CatRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatService {
    @Autowired
    public CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Cat addCatWithUpperName(String name, int age){
        name = name.toUpperCase();
        Cat cat = new Cat(name, age);
        cat.setIdentificator();
        catRepository.save(cat);

        return cat;
    }

    public List<Cat> changeAllUpperToLowerByName(String name) {
        List<Cat> cats = catRepository.findByName(name.toUpperCase());
        List<Cat> newCats = new ArrayList<>();

        for (Cat cat : cats) {
            cat.setName(cat.getName().toLowerCase());
            cat.setAge(cat.getAge());
            cat.setIdentificator();
            catRepository.save(cat);
            newCats.add(cat);
        }

        return newCats;
    }

}
