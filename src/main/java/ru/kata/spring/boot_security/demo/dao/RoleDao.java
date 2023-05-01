package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> getAll();

    public Role getRoleOnName(String name) throws Exception;

    public void create(Role role);
}