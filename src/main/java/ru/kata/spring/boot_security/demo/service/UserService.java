package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<User> getAll();

    public User get(Long id);

    public User getUserOnName(String name) throws Exception;

    public User create(User user);

    public void update(User user);

    public void delete(Long id);
}