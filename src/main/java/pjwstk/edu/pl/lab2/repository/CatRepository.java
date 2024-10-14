package pjwstk.edu.pl.lab2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pjwstk.edu.pl.lab2.model.Cat;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
    List<Cat> findByName(String name);
    List<Cat> findByAge(int age);
}
