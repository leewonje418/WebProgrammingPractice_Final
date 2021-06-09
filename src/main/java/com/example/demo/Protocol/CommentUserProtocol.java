package com.example.demo.Protocol;

import com.example.demo.Domain.Comment;
import lombok.Data;

@Data
public class CommentUserProtocol extends Comment {
    private String username;
    public CommentUserProtocol(Comment c, String username) {
        super(c);
        this.username = username;
    }
}
