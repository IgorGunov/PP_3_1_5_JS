package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private UserDao dao;

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User get(int id) {
        return dao.get(id);
    }

    @Override
    public User getUserOnName(String name) throws Exception {
        return dao.getUserOnName(name);
    }

    @Transactional
    @Override
    public User create(User user) {
        return dao.create(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = dao.getUserOnName(username);
        } catch (UsernameNotFoundException  e) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roleService.getAuthorities(user.getRoles())
        );
    }
}