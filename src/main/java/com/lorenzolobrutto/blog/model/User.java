package com.lorenzolobrutto.blog.model;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

@Entity
@Table(name = "users") // <-- cambia nome tabella
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Role> getRoles() {
        return new ArrayList<Role>(roles);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
