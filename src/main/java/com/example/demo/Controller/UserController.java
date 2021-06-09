package com.example.demo.Controller;


import com.example.demo.Domain.User;
import com.example.demo.Service.UserServiceImpl;
import com.example.demo.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user/add")
    public User add(@RequestBody User user) { return userService.add(user); }

    @PutMapping("/user/update/{id}")
    public Response update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/user/delete/{id}")
    public Response delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @GetMapping("/user/list")
    public List<User> list() {
        return userService.list();
    }
}
