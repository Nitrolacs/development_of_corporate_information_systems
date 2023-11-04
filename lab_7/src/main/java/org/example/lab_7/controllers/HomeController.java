package org.example.lab_7.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер домашней страницы приложения
 */
@Controller
public class HomeController {

    /**
     * Возвращает представление домашней страницы приложения
     * @return представление
     */
    @GetMapping("/")
    public String showHomePage() {
        return "bicycles/home";
    }
}
