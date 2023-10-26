package org.example.lab_6.controllers;

import org.example.lab_6.dao.BikeDAO;
import org.example.lab_6.models.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Класс контроллер
 */
@Controller
@RequestMapping("/bicycles")
public class BicyclesController {

    /**
     * Поле DAO класса
     */
    private final BikeDAO bikeDAO;

    /**
     * Внедрение при помощи Spring
     * @param bikeDAO
     */
    @Autowired
    public BicyclesController(BikeDAO bikeDAO) {
        this.bikeDAO = bikeDAO;
    }

    /**
     * Отображение всех велосипедов
     * @param model Модель
     * @return представление index
     */
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("bicycles", bikeDAO.findAll());
        return "bicycles/index";
    }

    /**
     * Показывает страницу с вводом цены, велосипеды дешевле которой нужно показать.
     * @return представление criterion
     */
    @GetMapping("/criterion")
    public String enterPrice() {
        return "bicycles/criterion";
    }

    /**
     * Возвращает представление с велосипедами, цена которых ниже введённой
     * @param price цена
     * @param model модель
     * @return представление criterion
     */
    @PostMapping("/criterion")
    public String bicyclesWithLowerPrice(@RequestParam Double price, Model model) {
        model.addAttribute("bicycles",
                bikeDAO.findAllBicyclesWherePriceIsLower(price));
        return "bicycles/index";
    }

    /**
     * Возвращает представление с информацией по велосипеду
     * @param id идентификатор велосипеда
     * @param model модель
     * @return представление
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("bike", bikeDAO.show(id));
        return "bicycles/show";
    }

    /**
     * Возвращает представление со страницей создания нового велосипеда
     * @param bike велосипед
     * @return представление
     */
    @GetMapping("/new")
    public String newBike(@ModelAttribute("bike") Bike bike) {
        return "bicycles/new";
    }

    /**
     * Вызывает у модели метод добавления нового велосипеда
     * @param bike Новый велосипед
     * @param bindingResult Результат проверки аннотации полей
     * @return редирект на другое представление
     */
    @PostMapping()
    public String create(@ModelAttribute("bike") @Valid Bike bike,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "bicycles/new";
        bikeDAO.insert(bike);
        return "redirect:/bicycles";
    }

    /**
     * Возвращает представление со страницей редактирования информации о велосипеде.
     * @param model модель
     * @param id идентификатор
     * @return представление
     */
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("bike", bikeDAO.show(id));
        return "bicycles/edit";
    }

    /**
     * Вызывает метод dao, который обновляет информацию о велосипеде
     * @param bike велосипед
     * @param bindingResult результат проверки привязки значений к полям Bike
     * @param id идентификатор
     * @return редирект на другое представление
     */
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("bike") @Valid Bike bike,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "bicycles/edit";

        bikeDAO.update(id, bike);
        return "redirect:/bicycles";
    }

    /**
     * Вызывает метод dao, который удаляет велосипед
     * @param id идентификатор
     * @return редирект на другое представление
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bikeDAO.delete(id);
        return "redirect:/bicycles";
    }
}
