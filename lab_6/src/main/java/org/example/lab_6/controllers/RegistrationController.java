package org.example.lab_6.controllers;

import org.example.lab_6.models.User;
import org.example.lab_6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Контроллер страницы регистрации
 */
@Controller
public class RegistrationController {

    /**
     * Подключение userService
     */
    @Autowired
    private UserService userService;

    /**
     * Получение страницы регистрации
     * @param model Модель
     * @return Представление со страницей регистрации
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "accounts/registration";
    }

    /**
     * Производит попытку сохранения нового аккаунта
     * @param userForm Форма аккаунта
     * @param bindingResult Результат проверки аннотации полей
     * @param model Модель
     * @return Представление
     */
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "accounts/registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError",
                    "Пароли не совпадают");
            return "accounts/registration";
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError",
                    "Пользователь с таким именем уже существует");
            return "accounts/registration";
        }

        return "redirect:/";
    }
}
