package pjwstk.edu.pl.mpr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pjwstk.edu.pl.mpr.model.Cat;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
    List<Cat> findByName(String name);
    List<Cat> findByAge(int age);
    List<Cat> findByNameContainingIgnoreCase(String name);
}
