package com.example.security.Controller;

import com.example.security.Model.AppUser;
import com.example.security.Model.Role;
import com.example.security.Model.mapRoleToUser;
import com.example.security.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService serviceLayer;

    @Autowired
    public UserController(UserService serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @GetMapping(path="/users")
    public ResponseEntity<List<AppUser>> getUser() {
        return ResponseEntity.ok().body(serviceLayer.getUsers());
    }

    @PostMapping(path="/user/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        serviceLayer.saveAppUser(user);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(user);
    }

    @PostMapping(path="/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        serviceLayer.saveRole(role);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(role);
    }

    @PostMapping(path="/role/addtouser")
    public ResponseEntity<?> roleToUser(@RequestBody mapRoleToUser mapUserRole ) {
        serviceLayer.addRoleToUser(mapUserRole.getUserName(), mapUserRole.getRoleName());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roleToUser").toUriString());
        return ResponseEntity.ok().build();
    }
}
