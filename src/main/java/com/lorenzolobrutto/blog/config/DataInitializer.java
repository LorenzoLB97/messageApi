package com.lorenzolobrutto.blog.config;

import com.lorenzolobrutto.blog.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.lorenzolobrutto.blog.repository.RoleRepository;
import com.lorenzolobrutto.blog.model.Role;
import com.lorenzolobrutto.blog.model.User;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se l'utente "admin" esiste già, altrimenti crealo
        if (userRepository.findByUsername("admin").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));  // Usa una password sicura
            admin.getRoles().add(adminRole);
            userRepository.save(admin);

            System.out.println("Admin user created with username: admin and password: admin");
        }
    }
}
