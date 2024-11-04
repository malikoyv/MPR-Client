package pjwstk.edu.pl.mpr.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pjwstk.edu.pl.mpr.exception.CatNotFoundException;
import pjwstk.edu.pl.mpr.exception.EmptyString;
import pjwstk.edu.pl.mpr.model.Cat;
import pjwstk.edu.pl.mpr.repository.CatRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatServiceTest {
    @Mock
    private CatRepository repository;

    @InjectMocks
    private CatService service;

    private final Cat cat = new Cat("KoTkA", 16);

    @Test
    public void getAllCatsSuccess(){
        when(repository.findAll()).thenReturn(List.of(cat));

        List<Cat> cats = service.getAll();

        assertNotNull(cats);
        assertEquals("KoTkA", cats.getFirst().getName());
    }

    @Test
    public void getAllCatsNotFound(){
        when(repository.findAll()).thenReturn(List.of());

        assertThrows(CatNotFoundException.class, () -> service.getAll());
    }

    @Test
    public void getByNameSuccess(){
        when(repository.findByName("KoTkA")).thenReturn(List.of(cat));

        List<Cat> cats = service.getByName("KoTkA");

        assertNotNull(cats);
        assertEquals("KoTkA", cats.getFirst().getName());
    }

    @Test
    public void getByNameNotFound() {
        when(repository.findByName("test")).thenReturn(List.of());

        assertThrows(CatNotFoundException.class, () -> service.getByName("test"));
    }

    @Test
    public void addCatSuccess(){
        when(repository.save(cat)).thenReturn(cat);

        Cat newCat = service.addCat(cat);

        assertNotNull(newCat);
        assertNotEquals(0, newCat.getIdentificator());
    }

    @Test
    public void getByAgeSuccess() {
        when(repository.findByAge(16)).thenReturn(List.of(cat));

        List<Cat> cats = service.getByAge(16);

        assertNotNull(cats);
        assertEquals("KoTkA", cats.getFirst().getName());
    }

    @Test
    public void getByAgeNotFound() {
        when(repository.findByAge(16)).thenReturn(List.of());

        assertThrows(CatNotFoundException.class, () -> service.getByAge(16));
    }

    @Test
    public void deleteByNameSuccess() {
        when(repository.findByName("KoTkA")).thenReturn(List.of(cat));
        service.deleteByName("KoTkA");

        verify(repository).deleteAll(List.of(cat));
    }

    @Test
    public void deleteByNameEmpty() {
        assertThrows(EmptyString.class, () -> service.deleteByName(""));
    }

    @Test
    public void changeNameSuccess() {
        when(repository.findByName("KoTkA")).thenReturn(List.of(cat));

        List<Cat> newCats = service.changeName("KoTkA", "kot");

        assertNotNull(newCats);
        assertEquals("kot", newCats.getFirst().getName());
    }

    @Test
    public void changeNameEmpty() {
        assertThrows(EmptyString.class, () -> service.changeName("", ""));
    }

    @Test
    public void changeNameNotFound() {
        when(repository.findByName("test")).thenReturn(List.of());

        assertThrows(CatNotFoundException.class, () -> service.changeName("test", "test2"));
    }

    @Test
    public void addCatWithUpperNameSuccess() {
        when(repository.save(any(Cat.class))).thenReturn(cat);

        Cat returnedCat = service.addCatWithUpperName(cat.getName(), cat.getAge());

        verify(repository).save(any(Cat.class));
        assertEquals(cat.getName().toUpperCase(), returnedCat.getName());
        assertNotEquals(0, returnedCat.getIdentificator());
    }

    @Test
    public void addCatWithUpperNameEmptyName() {
        assertThrows(EmptyString.class, () -> service.addCatWithUpperName("", 17));
    }

    @Test
    public void changeAllUpperToLowerByNameSuccess() {
        when(repository.save(any(Cat.class))).thenReturn(cat);
        when(repository.findByName(cat.getName().toUpperCase())).thenReturn(List.of(cat));

        List<Cat> cats = service.changeAllUpperToLowerByName(cat.getName());

        verify(repository).save(any(Cat.class));
        assertNotEquals(0, cats.size());
        assertEquals(cat.getName().toLowerCase(), cats.getFirst().getName());
        assertNotEquals(0, cats.getFirst().getIdentificator());
    }

    @Test
    public void changeAllUpperToLowerByNameEmpty(){
        assertThrows(EmptyString.class, () -> service.changeAllUpperToLowerByName(""));
    }

    @Test
    public void changeAllUpperToLowerByNameNotFound() {
        when(repository.findByName("TEST")).thenReturn(List.of());

        assertThrows(CatNotFoundException.class, () -> service.changeAllUpperToLowerByName("test"));
    }
}
