package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    private UserService service;

    @Autowired
    public void setUserService(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "user")
    public String getUsers(ModelMap model, Principal principal) {
        System.out.println(principal.getName());
        User user = service.getUserOnName(principal.getName());
        System.out.println(user);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping(value = "admin")
    public String admin(ModelMap model) {
        model.addAttribute("listUsers", service.getAll());
        return "users";
    }

    @GetMapping(value = "/{id}")
    public String getUsersOnId(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("listUsers", service.get(id));
        return "users";
    }

    @GetMapping(value = "/newUser")
    public String getWebpageAddUser(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping(value = "new")
    public String saveUser(@ModelAttribute("user") User user) {
        service.create(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/update/{id}")
    public String getWebpageEditUser(ModelMap model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", service.get(id));
        return "updateUser";
    }

    @PostMapping(value = "/update")
    public String updateUser(ModelMap model, @ModelAttribute("user") User user) {
        service.update(user);
        model.addAttribute("listUsers", service.getAll());
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(ModelMap model, @PathVariable("id") int id) {
        service.delete(id);
        model.addAttribute("listUsers", service.getAll());
        return "redirect:/admin";
    }
}