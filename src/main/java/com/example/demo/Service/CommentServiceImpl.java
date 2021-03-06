package com.example.demo.Service;

import com.example.demo.Domain.Comment;
import com.example.demo.Domain.User;
import com.example.demo.Protocol.CommentUserProtocol;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommentUserProtocol add(Comment comment) {
        return new CommentUserProtocol(
                commentRepository.save(comment),
                userRepository.findById(comment.getUser_id())
                        .map(found -> found.getUsername())
                        .orElse(null));
    }

    @Override
    public boolean delete(Long id) {
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CommentUserProtocol update(Long id, Comment comment) {
        Optional<User> user = userRepository.findById(comment.getUser_id());
        if(user.isPresent()) {
            return commentRepository.findById(id)
                    .map(found -> {
                        found.setComment(Optional.ofNullable(comment.getComment()).orElse(found.getComment()));
                        commentRepository.save(found);
                        return new CommentUserProtocol(found, user.get().getUsername());
                    })
                    .orElse(null);
        }
        return null;
    }

    @Override
    public CommentUserProtocol view(Long id) {
        return commentRepository.findById(id)
                .map(found -> {
                    Optional<User> user = userRepository.findById(found.getUser_id());
                    String username = user.isPresent() ? user.get().getUsername() : null;
                    return new CommentUserProtocol(found, username);
                })
                .orElse(null);
    }

    @Override
    public List<CommentUserProtocol> listAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentUserProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment -> {
            Optional<User> found = userRepository.findById(comment.getUser_id());
            String username = found.isPresent() ? found.get().getUsername() : null;
            cupList.add(new CommentUserProtocol(comment, username));
        });
        return cupList;
    }

}
