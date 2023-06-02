package ru.kata.spring.boot_security.demo.controller;//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.repository.RoleRepository;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    private final UserService userService;
//    private final RoleRepository roleRepository;
//
//    @Autowired
//    public AdminController(UserService userService, RoleRepository roleRepository) {
//        this.userService = userService;
//        this.roleRepository = roleRepository;
//    }
//
//
//    @GetMapping()
//    public String addUser(Model model) {
//
//        model.addAttribute("allUsers", userService.getListOfUsers());
//        model.addAttribute("allRoles", roleRepository.findAll());
//        model.addAttribute("newUser", new User());
//        return "admin_page";
//    }
//
//    @PostMapping()
//    public String createUser(@ModelAttribute("user") User user) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String editUser(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("roles", roleRepository.findAll());
//        model.addAttribute("user", userService.getUserById(id));
//        return "/edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
//        userService.saveUser(user);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
//}
//
//
