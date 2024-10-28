package pjwstk.edu.pl.mpr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjwstk.edu.pl.mpr.repository.CatRepository;

@Service
public class MyRestService {
    @Autowired
    public CatRepository catRepository;

    @Autowired
    public MyRestService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }
}
