package com.lorenzolobrutto.blog.service;

import com.lorenzolobrutto.blog.dto.RegisterRequest;
import com.lorenzolobrutto.blog.model.Role;
import com.lorenzolobrutto.blog.model.User;
import com.lorenzolobrutto.blog.repository.RoleRepository;
import com.lorenzolobrutto.blog.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username già in uso");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // assegna il ruolo USER di default
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        userRole.ifPresent(role -> user.getRoles().add(role));

        userRepository.save(user);
    }
}
