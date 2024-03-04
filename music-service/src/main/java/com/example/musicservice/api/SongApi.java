package com.example.musicservice.api;

import com.example.musicservice.dto.SongRecord;
import com.example.musicservice.dto.UserRegistrationRequestRecord;
import com.example.musicservice.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongApi {

    private  final SongService songService;

    @PostMapping("/artist/{artistId}")
    public ResponseEntity<SongRecord> createNewSong(@RequestBody SongRecord requestRecord,@PathVariable String artistId){

        songService.create(requestRecord,artistId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{songId}/user/{userId}/like")
    public  void userLikeASong(@PathVariable String songId,@PathVariable String userId){
        songService.userLikeASong(songId,userId);
    }


    @PutMapping("/{songId}/user/{userId}/dislike")
    public  void userDisLikeASong(@PathVariable String songId,@PathVariable String userId){
        songService.userDisLikeASong(songId,userId);
    }


}
