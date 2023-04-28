package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    public List<User> getAll();

    public User get(int id);

    public User getUserOnName(String name);

    public User create(User user);

    public void update(User user);

    public void delete(int id);
}