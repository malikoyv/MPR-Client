package pjwstk.edu.pl.lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjwstk.edu.pl.lab2.controller.MyRestController;
import pjwstk.edu.pl.lab2.model.Cat;
import pjwstk.edu.pl.lab2.repository.CatRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyRestService {
    @Autowired
    public CatRepository catRepository;

    @Autowired
    public MyRestService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

}
