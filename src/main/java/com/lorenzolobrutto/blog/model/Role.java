package com.lorenzolobrutto.blog.model;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // "ROLE_ADMIN", "ROLE_USER"

    public String getName(){
        return name;
    }

    public void setName(String role){
        this.name=role;
    }
}
