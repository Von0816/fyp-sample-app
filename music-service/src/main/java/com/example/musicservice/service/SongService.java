package com.example.musicservice.service;

import com.example.musicservice.dto.SongRecord;
import com.example.musicservice.model.Song;

public interface SongService {

    Song create(SongRecord songRecord,String artistId);
    void deleteById(String id);

    void userLikeASong(String songId,String userId);
    void userDisLikeASong(String songId,String userId);

}
