package com.example.musicservice.api;

import com.example.musicservice.model.Album;
import com.example.musicservice.model.Comment;
import com.example.musicservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentApi {

    private  final CommentService commentService;

    @PostMapping("/song/{songId}/user/{userId}")
    public ResponseEntity<Album> createNewComment(@RequestBody Comment comment, @PathVariable String songId, @PathVariable  String userId){

        commentService.create(comment,userId,songId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{id}")
    public void deleteById( @PathVariable String id){

        commentService.deleteById(id);
    }

}
