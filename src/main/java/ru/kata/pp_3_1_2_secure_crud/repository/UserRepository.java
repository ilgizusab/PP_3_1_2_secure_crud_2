package ru.kata.pp_3_1_2_secure_crud.repository;

import org.springframework.data.repository.CrudRepository;
import ru.kata.pp_3_1_2_secure_crud.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByName(String name);
    User findByEmail(String email);
}