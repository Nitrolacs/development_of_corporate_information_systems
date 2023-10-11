package org.example.lab_5.controllers;

import org.example.lab_5.dao.BikeDAO;
import org.example.lab_5.models.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;

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

    @GetMapping("/criterion")
    public String enterPrice() {
        return "bicycles/criterion";
    }

    @PostMapping("/criterion")
    public String bicyclesWithLowerPrice(@RequestParam Double price, Model model) {
        model.addAttribute("bicycles",
                bikeDAO.findAllBicyclesWherePriceIsLower(price));
        return "bicycles/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("bike", bikeDAO.show(id));
        return "bicycles/show";
    }

    @GetMapping("/new")
    public String newBike(@ModelAttribute("bike") Bike bike) {
        return "bicycles/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("bike") @Valid Bike bike,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "bicycles/new";
        bikeDAO.insert(bike);
        return "redirect:/bicycles";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("bike", bikeDAO.show(id));
        return "bicycles/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("bike") @Valid Bike bike,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "bicycles/edit";

        bikeDAO.update(id, bike);
        return "redirect:/bicycles";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bikeDAO.delete(id);
        return "redirect:/bicycles";
    }
}
