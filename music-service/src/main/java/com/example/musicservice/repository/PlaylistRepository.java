package com.example.musicservice.repository;

import com.example.musicservice.model.Playlist;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PlaylistRepository extends Neo4jRepository<Playlist,String> {



    @Query("MATCH (user: User {id: $userId}), (playlist: Playlist {id: $playlistId})" +
            " MERGE (user)-[:CREATED {createdAt: $createdAt}]->(playlist)")
    void addPlaylistAndUserRelationship(@Param("playlistId")String playlistId,
                                        @Param("userId")String userId,
                                        @Param("createdAt") LocalDateTime createdAt);


    @Query("MATCH (song: Song {id: $songId}), (playlist: Playlist {id: $playlistId})" +
            " MERGE (playlist)-[:CONTAINS {createdAt: $createdAt}]->(song)")
    void addSongToPlaylist(@Param("playlistId")String playlistId,
                                        @Param("songId")String songId,
                                        @Param("createdAt") LocalDateTime createdAt);
    @Query("MATCH (song: Song {id: $songId}) <-[relationship:CONTAINS]- (playlist: Playlist {id: $playlistId})" +
            " DELETE relationship")
    void removeSongFromPlaylist(@Param("playlistId")String playlistId,
                                @Param("songId")String songId);
}
