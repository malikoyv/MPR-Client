package pjwstk.edu.pl.mpr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pjwstk.edu.pl.mpr.model.Cat;

import java.util.List;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
    List<Cat> findByName(String name);
    List<Cat> findByAge(int age);
}
