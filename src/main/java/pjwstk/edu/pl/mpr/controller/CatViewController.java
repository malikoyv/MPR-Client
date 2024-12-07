package pjwstk.edu.pl.mpr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pjwstk.edu.pl.mpr.model.Cat;
import pjwstk.edu.pl.mpr.repository.CatRepository;
import pjwstk.edu.pl.mpr.service.CatService;

import java.util.List;

@Controller
public class CatViewController {
    @Autowired
    private CatRepository catRepository;

    @Autowired
    private CatService catService = new CatService(catRepository);

    @GetMapping("/")
    public String getAllCats(Model model) {
        model.addAttribute("allCats", catService.getAll());
        return "index";
    }

    @GetMapping("/getCatByName")
    public String getCatByName(@RequestParam(value = "name", required = false) String name, Model model) {
        if (name == null || name.isEmpty()) {
            model.addAttribute("allCats", catService.getAll());
        } else {
            model.addAttribute("allCats", catService.getByName(name));
        }

        return "index";
    }

    @GetMapping("/addNewCat")
    public String addNewCat(Model model) {
        model.addAttribute("cat", new Cat());

        return "newcat";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Cat cat) {
        if (cat.getId() != null) {
            Cat existingCat = catService.getById(cat.getId());
            existingCat.setName(cat.getName());
            existingCat.setAge(cat.getAge());
            existingCat.setIdentificator();
            catService.updateCat(existingCat);
        } else {
            catService.addCat(cat);
        }
        return "redirect:/";
    }


    @GetMapping("/deleteCatByName/{name}")
    public String deleteCatByName(@PathVariable(value = "name") String name) {
        catService.deleteByName(name);
        return "redirect:/";
    }

    @GetMapping("/updateCat/{id}")
    public String updateCat(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("cat", catService.getById(id));
        return "updatecat";
    }
}
