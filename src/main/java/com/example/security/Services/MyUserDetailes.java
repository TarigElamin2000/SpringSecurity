package com.example.security.Services;

import com.example.security.Interface.RepoAppUser;
import com.example.security.Model.AppUser;
import com.example.security.Model.UserPrinciples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailes implements UserDetailsService {
    private final RepoAppUser repoAppUser;

    @Autowired
    public MyUserDetailes(RepoAppUser repoAppUser) {
        this.repoAppUser = repoAppUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repoAppUser.findAppUserByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserPrinciples(appUser);
    }
}
