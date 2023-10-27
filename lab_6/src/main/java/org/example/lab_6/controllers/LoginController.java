package org.example.lab_6.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "accounts/login";
    }

    @PostMapping("/login")
    public String performLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpServletRequest request) {
        try {
            request.login(username, password);
            return "redirect:/";
        } catch (ServletException e) {
            // Обработка ошибки аутентификации
            return "accounts/login";
        }
    }
}

