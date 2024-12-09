package pjwstk.edu.pl.mpr.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pjwstk.edu.pl.mpr.models.Cat;

import java.util.List;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
    List<Cat> findByName(String name);
    List<Cat> findByAge(int age);
    List<Cat> findByNameContainingIgnoreCase(String name);
}
