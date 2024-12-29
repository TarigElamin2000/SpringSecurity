package com.example.security.Interface;

import com.example.security.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRole extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
}
