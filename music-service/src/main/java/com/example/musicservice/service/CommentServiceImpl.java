package com.example.musicservice.service;

import com.example.musicservice.model.Comment;
import com.example.musicservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements  CommentService{

    private final CommentRepository commentRepository;
    @Override
    public Comment create(Comment comment,String userId,String songId) {

        Comment comment1 = Comment.builder()
                .text(comment.getText())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .edited(false).build();

        Comment saved = commentRepository.save(comment1);
        commentRepository.addCommentRelationship(saved.getId(),songId,userId);


        return saved;
    }

    @Override
    public Comment update(String id, Comment comment) {


        return null;
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
