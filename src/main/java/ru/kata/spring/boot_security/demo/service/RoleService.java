package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    public List<Role> getAll();

    public Role getRoleOnName(String name) throws Exception;

    public void create(Role role);

    public Set<GrantedAuthority> getAuthorities(Set<Role> roles);
}
