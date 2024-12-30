package com.example.security.config;

import com.example.security.Model.AppUser;
import com.example.security.Model.Role;
import com.example.security.Services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;


@Configuration
public class configration {

    private UserService userService;

    @Bean
    CommandLineRunner run(UserService userService){
        return args->{
            userService.saveRole(new Role("ADMIN"));
            userService.saveRole(new Role("USER"));
            userService.saveRole(new Role("Editor"));
            userService.saveRole(new Role("Viewer"));

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

            AppUser user1 = new AppUser("Tariq","TariqElamin", "123", new ArrayList<>());
            user1.setPassword(encoder.encode(user1.getPassword()));

            userService.saveAppUser(user1);
            userService.saveAppUser(new AppUser("Chase","ChaseTanner", "123", new ArrayList<>()));
            userService.saveAppUser(new AppUser("mohamed","MohamedAbdul", "123", new ArrayList<>()));
            userService.saveAppUser(new AppUser("Razan","RazanTariq", "123", new ArrayList<>()));

            userService.addRoleToUser("TariqElamin", "ADMIN");
            userService.addRoleToUser("ChaseTanner", "USER");
            userService.addRoleToUser("MohamedAbdul", "USER");
            userService.addRoleToUser("RazanTariq", "USER");
            userService.addRoleToUser("TariqElamin", "Editor");
            userService.addRoleToUser("ChaseTanner", "Viewer");
            userService.addRoleToUser("MohamedAbdul", "Viewer");
            userService.addRoleToUser("RazanTariq", "Viewer");
        };
    }
}
