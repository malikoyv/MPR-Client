package pjwstk.edu.pl.lab2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MyRestService {
    public List<Cat> cats = new ArrayList<>(Arrays.asList(new Cat("Kot", 21), new Cat("Mursik", 22)));
}
