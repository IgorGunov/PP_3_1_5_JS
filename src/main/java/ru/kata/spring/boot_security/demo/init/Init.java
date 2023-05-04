package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class Init {
    private UserService service;
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleServiceImp roleService, UserServiceImp service) {
        this.roleService = roleService;
        this.service = service;
    }

    @PostConstruct
    public void postConstruct() throws Exception {

        Role userRole = new Role();
        userRole.setName("ROLE_USER");


        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");

        Set<Role> userRoles = Set.of(userRole);
        Set<Role> adminRoles = Set.of(adminRole);

        User user = new User("user", "user", "123");
        user.setPassword(user.getPassword());
        user.setRoles(userRoles);
        service.create(user);

        User admin = new User("admin", "admin", "admin");
        admin.setPassword(admin.getPassword());
        admin.setRoles(adminRoles);
        service.create(admin);
    }
}