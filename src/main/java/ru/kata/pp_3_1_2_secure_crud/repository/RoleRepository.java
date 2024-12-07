package ru.kata.pp_3_1_2_secure_crud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kata.pp_3_1_2_secure_crud.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
