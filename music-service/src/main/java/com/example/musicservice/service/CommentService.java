package com.example.musicservice.service;

import com.example.musicservice.model.Comment;

public interface CommentService {

    Comment create(Comment comment,String userId,String songId);
    Comment update(String id,Comment comment );

    void deleteById(String id);
}
