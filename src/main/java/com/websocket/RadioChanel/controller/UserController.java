package com.websocket.RadioChanel.controller;

import com.websocket.RadioChanel.model.User;
import com.websocket.RadioChanel.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Data
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("users/{userId}")
    public User get(@PathVariable long id) {
        return userService.get(id);
    }


    @PostMapping("users")
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("users/{userId}")
    public void deleteById(@PathVariable Long userId) {
        userService.delete(userId);
    }

}

