package ru.kata.pp_3_1_2_secure_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.pp_3_1_2_secure_crud.model.Role;
import ru.kata.pp_3_1_2_secure_crud.model.User;
import ru.kata.pp_3_1_2_secure_crud.repository.RoleRepository;
import ru.kata.pp_3_1_2_secure_crud.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}