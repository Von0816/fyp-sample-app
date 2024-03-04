package com.example.musicservice.service;

import com.example.musicservice.model.Playlist;

public interface PlaylistService {

    Playlist create(Playlist playlist, String userId);
    void deleteById(String id);
    void addSongIntoPlaylist(String playlistId, String songId);
    void removeSongFromPlaylist(String playlistId, String songId);

}
