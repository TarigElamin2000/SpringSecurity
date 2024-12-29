package com.example.security.Interface;

import com.example.security.Model.AppUser;
import com.example.security.Model.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepoServices {
    AppUser saveAppUser(AppUser appUser); // Parameter name updated to camelCase
    AppUser getUser(String username);
    List<AppUser> getUsers();

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);
}

