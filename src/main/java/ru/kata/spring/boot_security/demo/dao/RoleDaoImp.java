package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAll() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleOnName(String name) throws Exception {
        for (Role user : getAll()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new Exception("такой роли нет");
    }

    @Override
    public void create(Role role) {
        entityManager.persist(role);
    }
}