package com.example.security.Services;

import com.example.security.Interface.RepoAppUser;
import com.example.security.Interface.RepoRole;
import com.example.security.Interface.RepoServices;
import com.example.security.Model.AppUser;
import com.example.security.Model.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
@Transactional
@Service
public class UserService implements RepoServices {

    private final RepoAppUser repoAppUser;
    private final RepoRole repoRole;
    private final AuthenticationManager authManager;
    private final JwtToken jwtToken;

    @Autowired
    public UserService(RepoAppUser repoAppUser, RepoRole repoRole, AuthenticationManager authManager, JwtToken jwtToken) {
        this.repoAppUser = repoAppUser;
        this.repoRole = repoRole;
        this.authManager = authManager;
        this.jwtToken = jwtToken;
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

    public String authUser(AppUser user) {
        Authentication authResult = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authResult.isAuthenticated()) {
            return jwtToken.generateToken(user.getUsername());
        }
        else {
            return "Wrong Password";
        }
    }
}
