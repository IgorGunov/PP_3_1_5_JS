package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class Init {
    private UserService service;

    @Autowired
    private RoleDao dao;

    @Autowired
    public void setUserService(UserService service) {
        this.service = service;
    }

    @PostConstruct
    public void postConstruct() {
        User user = new User("Igor","Gunov", "123");
        user.setPassword(user.getPassword());

        User user2 = new User("Micha","Korolev", "123");
        user2.setPassword(user2.getPassword());

        User user3 = new User("user","user", "user");
        user3.setPassword(user3.getPassword());

        User user4 = new User("admin","admin", "admin");
        user4.setPassword(user4.getPassword());

        dao.create(new Role("ROLE_USER"));
        dao.create(new Role("ROLE_ADMIN"));

        Set<Role> roles = new HashSet<>();
        roles.add(dao.getRoleOnName("ROLE_USER"));

        Set<Role> roles2 = new HashSet<>();
        roles2.add(dao.getRoleOnName("ROLE_ADMIN"));

        user.setRoles(roles);
        user2.setRoles(roles);
        user3.setRoles(roles);
        user4.setRoles(roles2);
        service.create(user);
        service.create(user2);
        service.create(user4);


    }
}