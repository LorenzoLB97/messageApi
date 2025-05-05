package com.lorenzolobrutto.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lorenzolobrutto.blog.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

