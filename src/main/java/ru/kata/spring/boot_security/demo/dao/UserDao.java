package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAll();

    public User get(int id);

    public User getUserOnName(String name) throws UsernameNotFoundException;

    public User create(User user);

    public void update(User user);

    public void delete(int id);
}