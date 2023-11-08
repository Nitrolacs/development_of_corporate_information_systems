package org.example.lab_8.controllers;

import org.example.lab_8.dao.BikeDAO;
import org.example.lab_8.models.Bike;
import org.example.lab_8.models.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
     * RabbitTemplate
     */
    private final RabbitTemplate rabbitTemplate;

    /**
     * Внедрение при помощи Spring
     *
     * @param bikeDAO
     */
    @Autowired
    public BicyclesController(BikeDAO bikeDAO, RabbitTemplate rabbitTemplate) {
        this.bikeDAO = bikeDAO;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Возвращает список всех велосипедов в формате JSON
     *
     * @return список объектов Bike
     */
    @ResponseBody
    @GetMapping(headers = {"Accept=application/json"})
    public List<Bike> getBicycles() {
        return bikeDAO.getBicycles();
    }

    /**
     * Отображение всех велосипедов
     *
     * @param model Модель
     * @return представление index
     */
    @GetMapping(headers = {"Accept=text/html"})
    public String getBicycles(Model model) {
        model.addAttribute("bicycles", bikeDAO.getBicycles());
        return "bicycles/index";
    }

    /**
     * Показывает страницу с вводом цены, велосипеды дешевле которой нужно показать.
     *
     * @return представление criterion
     */
    @GetMapping("/criterion")
    public String enterPrice() {
        return "bicycles/criterion";
    }

    /**
     * Возвращает представление с велосипедами, цена которых ниже введённой
     *
     * @param price цена
     * @param model модель
     * @return представление criterion
     */
    @GetMapping("/criterion/show")
    public String bicyclesWithLowerPrice(@RequestParam Double price, Model model) {
        model.addAttribute("bicycles",
                bikeDAO.findAllBicyclesWherePriceIsLower(price));
        return "bicycles/index";
    }

    /**
     * Возвращает велосипед по id в формате JSON
     *
     * @param id идентификатор велосипеда
     * @return объект велосипеда
     */
    @ResponseBody
    @GetMapping(value = "/{id}", headers = {"Accept=application/json"})
    public Bike getBike(@PathVariable("id") int id) {
        return bikeDAO.getBikeById(id);
    }

    /**
     * Возвращает представление с информацией по велосипеду
     *
     * @param id    идентификатор велосипеда
     * @param model модель
     * @return представление
     */
    @GetMapping(value = "/{id}", headers = {"Accept=text/html"})
    public String getBike(@PathVariable("id") int id, Model model) {
        model.addAttribute("bike", bikeDAO.getBikeById(id));
        return "bicycles/show";
    }

    /**
     * Возвращает представление со страницей создания нового велосипеда
     *
     * @param bike велосипед
     * @return представление
     */
    @GetMapping("/new")
    public String newBike(@ModelAttribute("bike") Bike bike) {
        return "bicycles/new";
    }

    /**
     * Добавляет новый велосипед в базу данных и возвращает его в формате JSON
     *
     * @param bike     велосипед который будет добавлен в таблицу
     * @param result   результат проверки аннотации полей
     * @param response объект HttpServletResponse
     * @return объект Bike, представляющий созданный велосипед
     * @throws BindException если не прошли валидацию
     */
    @PostMapping(headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Bike createBike(@Valid @RequestBody Bike bike, BindingResult result, HttpServletResponse response) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        Bike createdBike = bikeDAO.insert(bike);
        rabbitTemplate.convertAndSend("bike-queue",
                new Message("В базу данных была добавлена " +
                        "следующая запись: ", createdBike));
        response.setHeader("Location", "/bicycles/" + createdBike.getId());
        return createdBike;
    }


    /**
     * Вызывает у модели метод добавления нового велосипеда
     *
     * @param bike          Новый велосипед
     * @param bindingResult Результат проверки аннотации полей
     * @return редирект на другое представление
     */
    @PostMapping(headers = {"Accept=text/html"})
    public String createBike(@ModelAttribute("bike") @Valid Bike bike,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "bicycles/new";
        Bike createdBike = bikeDAO.insert(bike);
        rabbitTemplate.convertAndSend("bike-queue",
                new Message("В базу данных была добавлена " +
                        "следующая запись: ", createdBike));
        return "redirect:/bicycles";
    }

    /**
     * Возвращает представление со страницей редактирования информации о велосипеде.
     *
     * @param model модель
     * @param id    идентификатор
     * @return представление
     */
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("bike", bikeDAO.getBikeById(id));
        return "bicycles/edit";
    }

    /**
     * Вызывает метод dao, который обновляет информацию о велосипеде
     *
     * @param bike          велосипед
     * @param bindingResult результат проверки привязки значений к полям Bike
     * @param id            идентификатор
     * @return редирект на другое представление
     */
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("bike") @Valid Bike bike,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "bicycles/edit";

        Bike oldBike = bikeDAO.getBikeById(id);
        rabbitTemplate.convertAndSend("bike-queue",
                new Message("В базе данных была изменена " +
                        "запись " + oldBike + " на: ", bike));
        bikeDAO.update(id, bike);
        return "redirect:/bicycles";
    }

    /**
     * Удаляет велосипед из таблицы по id
     *
     * @param id идентификатор велосипеда, который нужно удалить
     */
    @DeleteMapping(value = "/{id}", headers = {"Accept=application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBike(@PathVariable("id") int id) {
        Bike deletedBike = bikeDAO.getBikeById(id);
        rabbitTemplate.convertAndSend("bike-queue",
                new Message("Из базы данных была удалена " +
                        "следующая запись: ", deletedBike));
        bikeDAO.delete(id);
    }

    /**
     * Вызывает метод dao, который удаляет велосипед
     *
     * @param id идентификатор
     * @return редирект на другое представление
     */
    @DeleteMapping(value = "/{id}", headers = {"Accept=text/html"})
    public String delete(@PathVariable("id") int id) {
        Bike deletedBike = bikeDAO.getBikeById(id);
        rabbitTemplate.convertAndSend("bike-queue",
                new Message("Из базы данных была удалены " +
                        "следующая запись: ", deletedBike));
        bikeDAO.delete(id);
        return "redirect:/bicycles";
    }

    /**
     * Обновляет информацию о велосипеде по его идентификатору.
     *
     * @param id      идентификатор велосипеда, который нужно обновить
     * @param newBike объект Bike, содержащий новые данные о велосипеде
     * @param result  объект BindingResult, содержащий результаты валидации newBike
     * @return ResponseEntity с кодом состояния NO_CONTENT, если обновление прошло успешно, или с кодом состояния BAD_REQUEST и сообщением об ошибке, если валидация не прошла
     */
    @PutMapping(value = "/{id}", headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateBike(@PathVariable("id") int id, @Valid @RequestBody Bike newBike, BindingResult result) {
        if (result.hasErrors()) {
            // Получить все ошибки валидации
            List<ObjectError> errors = result.getAllErrors();
            String errorMessage = errors.stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            // Вернуть сообщение об ошибке
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
        Bike oldBike = bikeDAO.getBikeById(id);
        rabbitTemplate.convertAndSend("bike-queue",
                new Message("В базе данных была изменена " +
                        "запись " + oldBike + " на: ", newBike));
        bikeDAO.update(newBike.getId(), newBike);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Обрабатывает запрос на покупку велосипеда
     *
     * @param id идентификатор велосипеда
     * @return редирект на страницу велосипедов
     */
    @PostMapping("/{id}/buy")
    public String buyBike(@PathVariable("id") int id) {
        Bike bike = bikeDAO.getBikeById(id);
        if (bike != null) {
            rabbitTemplate.convertAndSend("bike-queue",
                    new Message("Велосипед с ID " + id + " был " +
                            "куплен: ", bike));
            bikeDAO.delete(id);
        }
        return "redirect:/bicycles";
    }
}
