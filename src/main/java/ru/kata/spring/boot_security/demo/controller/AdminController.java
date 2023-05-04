package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private UserService service;

    private RoleService roleServiceImp;

    @Autowired
    public void setRoleServiceImp(RoleService roleServiceImp) {
        this.roleServiceImp = roleServiceImp;
    }

    @Autowired
    public void setUserService(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "admin")
    public String getUsers(ModelMap model) {
        model.addAttribute("listUsers", service.getAll());
        return "users";
    }

    @GetMapping(value = "/view/{id}")
    public String getUsersOnId(ModelMap model, @PathVariable("id") Long id) {
        User user = service.get(id);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping(value = "/newUser")
    public String getWebpageAddUser(@ModelAttribute("user") User user, ModelMap model) {
        List<Role> roles = roleServiceImp.getAll();
        model.addAttribute("roles", roles);
        return "newUser";
    }

    @PostMapping(value = "new")
    public String saveUser(HttpServletRequest request, @ModelAttribute("user") User user) throws Exception {
        String[] selectedRoles = request.getParameterValues("selectedRoles");
        Set<Role> roles = roleServiceImp.getSetRole(selectedRoles);
        user.setRoles(roles);
        service.create(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/update/{id}")
    public String getWebpageEditUser(ModelMap model, @PathVariable(value = "id") Long id) {
        model.addAttribute("user", service.get(id));
        List<Role> roles = roleServiceImp.getAll();
        model.addAttribute("roles", roles);
        return "updateUser";
    }

    @PostMapping(value = "/update")
    public String updateUser(ModelMap model, @ModelAttribute("user") User user, HttpServletRequest request) throws Exception {
        String[] selectedRoles = request.getParameterValues("selectedRoles");
        Set<Role> roles = roleServiceImp.getSetRole(selectedRoles);
        user.setRoles(roles);
        service.update(user);
        model.addAttribute("listUsers", service.getAll());
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(ModelMap model, @PathVariable("id") Long id) {
        service.delete(id);
        model.addAttribute("listUsers", service.getAll());
        return "redirect:/admin";
    }
}