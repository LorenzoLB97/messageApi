package com.lorenzolobrutto.blog.controller;

import com.lorenzolobrutto.blog.model.Role;
import com.lorenzolobrutto.blog.model.User;
import com.lorenzolobrutto.blog.repository.RoleRepository;
import com.lorenzolobrutto.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    // mostra signup.html
    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    // processa la registrazione
    @PostMapping("/signup")
    public String processSignup(
            @RequestParam String username,
            @RequestParam String password
    ) {
        // già esistente?
        if (userRepository.findByUsername(username).isPresent()) {
            return "redirect:/auth/signup?error=username_taken";
        }

        // crea utente
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        // assegna ruolo USER
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        if (userRole.isEmpty()) {
            return "redirect:/auth/signup?error=role_missing";
        }
        user.setRoles(Set.of(userRole.get()));
        userRepository.save(user);

        return "redirect:/";
    }

    // mostra la pagina di login
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // crea login.html in templates/
    }

    // (opzionale) gestisci il form di login via Spring Security
    @PostMapping("/login")
    public String processLogin() {
        // lasciamo che Spring Security gestisca il login vero e proprio
        return "redirect:/";
    }
}
