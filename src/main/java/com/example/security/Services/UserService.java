package com.example.security.Services;

import com.example.security.Interface.RepoAppUser;
import com.example.security.Interface.RepoRole;
import com.example.security.Interface.RepoServices;
import com.example.security.Model.AppUser;
import com.example.security.Model.Role;
import com.example.security.Model.UserPrinciples;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Transactional
@Service
public class UserService implements RepoServices {

    private final RepoAppUser repoAppUser;
    private final RepoRole repoRole;

    @Autowired
    public UserService(RepoAppUser repoAppUser, RepoRole repoRole) {
        this.repoAppUser = repoAppUser;
        this.repoRole = repoRole;
    }

    @Override
    public AppUser saveAppUser(AppUser user) {
        return repoAppUser.save(user);
    }

    @Override
    public AppUser getUser(String username) {
        return repoAppUser.findAppUserByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        return repoAppUser.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        return repoRole.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = repoAppUser.findAppUserByUsername(username);
        Role roleOfUser = repoRole.findRoleByName(roleName);
        appUser.getRoles().add(roleOfUser);
    }
}
