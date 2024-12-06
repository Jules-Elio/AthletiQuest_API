package com.athletiquest.athletiquest_api.controller;

import com.athletiquest.athletiquest_api.dto.entity.User;
import com.athletiquest.athletiquest_api.dto.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping()
    public List<User> getUsers() {
        return service.findAll();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return service.findById(userId);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return service.save(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        service.delete(userId);
    }
}
