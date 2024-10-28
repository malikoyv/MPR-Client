package pjwstk.edu.pl.lab2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;
import org.springframework.boot.test.context.SpringBootTest;
import pjwstk.edu.pl.lab2.model.Cat;
import pjwstk.edu.pl.lab2.repository.CatRepository;

import java.util.Arrays;
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
    public void catNameToUpperSuccess() {
        when(repository.save(any(Cat.class))).thenReturn(cat);

        Cat returnedCat = service.addCatWithUpperName(cat.getName(), cat.getAge());

        verify(repository).save(any(Cat.class));
        assertEquals(cat.getName().toUpperCase(), returnedCat.getName());
        assertNotEquals(0, returnedCat.getIdentificator());
    }

    @Test
    public void catNameToLowerSuccess() {
        when(repository.save(any(Cat.class))).thenReturn(cat);
        when(repository.findByName(cat.getName().toUpperCase())).thenReturn(List.of(cat));

        List<Cat> cats = service.changeAllUpperToLowerByName(cat.getName());

        verify(repository).save(any(Cat.class));
        assertNotEquals(0, cats.size());
        assertEquals(cat.getName().toLowerCase(), cats.getFirst().getName());
        assertNotEquals(0, cats.getFirst().getIdentificator());
    }
}
