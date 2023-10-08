package org.example.lab_5.controllers;

import org.example.lab_5.dao.BikeDAO;
import org.example.lab_5.models.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bicycles")
public class BicyclesController {
    private final BikeDAO bikeDAO;

    @Autowired
    public BicyclesController(BikeDAO bikeDAO) {
        this.bikeDAO = bikeDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("bicycles", bikeDAO.findAll());
        return "bicycles/index";
    }

    /**
    //@GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("bike", bikeDAO.show(id));
        return "bicycles/show";
    }**/

    @GetMapping("/new")
    public String newBike(@ModelAttribute("bike") Bike bike) {
        return "bicycles/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("bike") Bike bike) {
        bikeDAO.insert(bike);
        return "redirect:/bicycles";
    }
}
