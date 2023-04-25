package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    public List<User> getAll();
    public User get(int id);
    public User getUserOnName(String name);

    public void create(User user);

    public void update(User user);

    public void delete(int id);
}
