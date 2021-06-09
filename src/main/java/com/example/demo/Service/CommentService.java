package com.example.demo.Service;

import com.example.demo.Domain.Comment;
import com.example.demo.Protocol.CommentUserProtocol;

import java.util.List;

public interface CommentService {
    CommentUserProtocol add(Comment comment);

    boolean delete(Long id);

    CommentUserProtocol update(Long id, Comment comment);

    CommentUserProtocol view(Long id);

    List<CommentUserProtocol> listAllComments();
}
