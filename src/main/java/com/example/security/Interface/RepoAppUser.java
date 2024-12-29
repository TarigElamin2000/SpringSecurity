package com.example.security.Interface;

import com.example.security.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoAppUser extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);
}

