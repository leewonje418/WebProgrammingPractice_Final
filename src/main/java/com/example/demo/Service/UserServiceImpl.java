package com.example.demo.Service;

import com.example.demo.Domain.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User add(User user) {
        Optional<User> found = userRepository.findByEmail(user.getEmail());
        if(found.isPresent()) return null;
        return userRepository.save(user);
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public Response update(Long id, User user) {
        userRepository.findById(id).map(found -> {
            found.setUsername(Optional.ofNullable(user.getUsername()).orElse(found.getUsername()));
            found.setEmail(Optional.ofNullable(user.getEmail()).orElse(found.getEmail()));
            found.setUsername(Optional.ofNullable(user.getUsername()).orElse(found.getUsername()));
            userRepository.save(found);
            return new Response(HttpStatus.MOVED_PERMANENTLY, "계정 삭제 성공!");
        }).orElse(null);
        return null;
    }

    @Override
    public Response delete(Long id) {
        try {
            userRepository.deleteById(id);
            return new Response(HttpStatus.MOVED_PERMANENTLY, "계정 삭제 성공!");
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User View(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? user.get() : null;
    }

    @Override
    public List<User> list() {
        return userRepository.findAll();
    }
}
