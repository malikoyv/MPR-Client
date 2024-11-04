package pjwstk.edu.pl.mpr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjwstk.edu.pl.mpr.model.Cat;
import pjwstk.edu.pl.mpr.repository.CatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MyRestService {
    @Autowired
    public CatRepository catRepository;

    @Autowired
    public MyRestService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public Iterable<Cat> getAll(){
        return catRepository.findAll();
    }

    public List<Cat> getByName(final String name) {
        return catRepository.findByName(name);
    }

    public Cat addCat(final Cat cat) {
        cat.setIdentificator();
        return catRepository.save(cat);
    }

    public List<Cat> getByAge(int age) {
        return catRepository.findByAge(age);
    }

    public List<Cat> deleteByName(final String name) {
        List<Cat> catToDelete = getByName(name);
        catRepository.deleteAll(catToDelete);

        return catToDelete;
    }

    public List<Cat> changeName(final String oldName, final String newName) {
        List<Cat> cats = catRepository.findByName(oldName);
        if (!cats.isEmpty()) {
            for (Cat cat : cats) {
                if (!cat.getName().equals(newName)) {
                    cat.setName(newName);
                    catRepository.save(cat);
                    cat.setIdentificator();
                }
            }
        }

        return cats;
    }
}
