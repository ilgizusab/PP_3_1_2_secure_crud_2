package ru.kata.pp_3_1_2_secure_crud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.pp_3_1_2_secure_crud.model.Role;
import ru.kata.pp_3_1_2_secure_crud.model.User;
import ru.kata.pp_3_1_2_secure_crud.repository.RoleRepository;
import ru.kata.pp_3_1_2_secure_crud.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        if (roleRepository.findByName("ROLE_USER") == null) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        if (userRepository.findByName("admin") == null) {

            //добавление админа в БД
            User admin = new User();
            admin.setName("admin");
            admin.setPassword(passwordEncoder.encode("admin"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(roleRepository.findByName("ROLE_ADMIN"));
            admin.setRoles(adminRoles);

            userRepository.save(admin);


        }

        if (userRepository.findByName("user") == null) {
            //добавление юзера в БД
            User user = new User();
            user.setName("user");
            user.setPassword(passwordEncoder.encode("user"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleRepository.findByName("ROLE_USER"));
            user.setRoles(userRoles);

            userRepository.save(user);
        }
    }
}