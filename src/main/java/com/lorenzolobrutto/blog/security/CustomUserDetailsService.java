package com.lorenzolobrutto.blog.security;

import com.lorenzolobrutto.blog.model.User;
import com.lorenzolobrutto.blog.model.Role;
import com.lorenzolobrutto.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + username));

        // Mappa i tuoi ruoli in GrantedAuthority
        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());

            System.out.println("GGGGGGGGGG Utente trovato: " + user.getUsername());
            System.out.println("GGGGGGGGGG Ruoli: " + user.getRoles().stream().map(Role::getName).toList());

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getUsername())
            .password(user.getPassword())   // deve essere già codificata con BCrypt
            .authorities(authorities)
            .build();
    }
}
