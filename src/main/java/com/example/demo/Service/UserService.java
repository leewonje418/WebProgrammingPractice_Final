package com.example.demo.Service;

import com.example.demo.Domain.User;
import com.example.demo.http.Response;

import java.util.List;

public interface UserService {
    User add(User user);
    User login(String email, String password);
    Response update(Long id, User user);
    Response delete(Long id);
    User View(Long id);
    List<User> list();
}