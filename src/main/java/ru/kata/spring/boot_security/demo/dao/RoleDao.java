package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Role> getAll() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }

    @Transactional
    public Role getRoleOnName(String name) {
        for (Role user : getAll()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Transactional
    public void create(Role role) {
        entityManager.persist(role);
    }

}
