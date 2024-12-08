package ru.kata.pp_3_1_2_secure_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.pp_3_1_2_secure_crud.model.Role;
import ru.kata.pp_3_1_2_secure_crud.model.User;
import ru.kata.pp_3_1_2_secure_crud.repository.RoleRepository;
import ru.kata.pp_3_1_2_secure_crud.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            String newPassword = passwordEncoder.encode(user.getPassword());
            if (!user.getPassword().equals(newPassword)) {
                user.setPassword(newPassword);
            }
            if (user.getPassword().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            }
            user.setRoles(existingUser.getRoles());
        }
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByName(username);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id.intValue());
    }

    public User findById(Long id) {
        return userRepository.findById(id.intValue()).orElse(null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (User user:userRepository.findAll()){
            users.add(user);
        }
        return users;
    }

    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        for (Role role:roleRepository.findAll()){
            roles.add(role);
        }
        return roles;
    }

    public void updateUser(User user, Integer roleId, String password) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            if (password != null && !password.isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(password));
            }
            Role role = roleRepository.findById(roleId).orElse(null);
            if (role != null) {
                existingUser.getRoles().clear();
                existingUser.getRoles().add(role);
            }
            userRepository.save(existingUser);
        }
    }

    public User newUser() {
        User user;
        user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        return user;
    }
}