package org.example.lab_6.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        if (request.getUserPrincipal() != null) {
            // Если пользователь уже вошел в систему, перенаправляем его на главную страницу
            return "redirect:/";
        }

        // Если пользователь еще не вошел в систему, показываем ему страницу входа
        return "accounts/login";
    }

    @PostMapping("/login")
    public String performLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpServletRequest request, Model model) {
        try {
            request.login(username, password);
            return "redirect:/";
        } catch (ServletException e) {
            // Обработка ошибки аутентификации
            model.addAttribute("error", true);
            return "accounts/login";
        }
    }
}

