package pjwstk.edu.pl.mpr.service;

import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjwstk.edu.pl.mpr.exception.CatIsNullException;
import pjwstk.edu.pl.mpr.exception.CatNotFoundException;
import pjwstk.edu.pl.mpr.exception.EmptyString;
import pjwstk.edu.pl.mpr.model.Cat;
import pjwstk.edu.pl.mpr.repository.CatRepository;

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

    public List<Cat> getAll(){
        List<Cat> allCats = new ArrayList<>();
        catRepository.findAll().forEach(allCats::add);

        if (allCats.isEmpty()){
            throw new CatNotFoundException("There is no cat in the database");
        }

        return allCats;
    }

    public List<Cat> getByName(final String name) {
        List<Cat> cats = catRepository.findByName(name);

        if (cats == null || cats.isEmpty()){
            throw new CatNotFoundException("Cat not found");
        }

        return cats;
    }

    public Cat addCat(Cat cat) {
        cat.setIdentificator();
        return catRepository.save(cat);
    }

    public List<Cat> getByAge(int age) {
        List<Cat> cats = catRepository.findByAge(age);
        if (cats == null || cats.isEmpty()){
            throw new CatNotFoundException("Cat not found");
        }

        return cats;
    }

    public List<Cat> deleteByName(final String name) {
        List<Cat> catToDelete = catRepository.findByName(name);

        if (name.isEmpty()){
            throw new EmptyString("Cat's name is empty");
        }
        catRepository.deleteAll(catToDelete);

        return catToDelete;
    }

    public List<Cat> changeName(final String oldName, final String newName) {
        if (oldName.isEmpty() || newName.isEmpty()){
            throw new EmptyString("Your name is empty. Please write cat's name!");
        }

        List<Cat> cats = catRepository.findByName(oldName);

        if (cats == null || cats.isEmpty()){
            throw new CatNotFoundException("Cat not found");
        }

        for (Cat cat : cats) {
            if (!cat.getName().equals(newName)) {
                cat.setName(newName);
                catRepository.save(cat);
                cat.setIdentificator();
            }
        }

        return cats;
    }

    public Cat addCatWithUpperName(String name, int age){
        if (name.isEmpty()){
            throw new EmptyString("Your name is empty. Please write cat's name!");
        }

        name = name.toUpperCase();
        Cat cat = new Cat(name, age);
        cat.setIdentificator();
        catRepository.save(cat);

        return cat;
    }

    public List<Cat> changeAllUpperToLowerByName(String name) {
        if (name.isEmpty()){
            throw new EmptyString("Your name is empty. Please write cat's name!");
        }

        List<Cat> cats = catRepository.findByName(name.toUpperCase());

        if (cats.isEmpty()) {
            throw new CatNotFoundException("Cat not found");
        }

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
