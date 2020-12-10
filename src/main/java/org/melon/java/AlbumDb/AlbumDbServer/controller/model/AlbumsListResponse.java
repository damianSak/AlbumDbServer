package org.melon.java.AlbumDb.AlbumDbServer.controller.model;

import org.melon.java.AlbumDb.AlbumDbServer.model.Album;

import java.util.List;

public class AlbumsListResponse {

    private String message;
    private List<Album> albumList;


    public AlbumsListResponse(List<Album> albumList, String message) {
        this.message = message;
        this.albumList = albumList;
    }

    public static AlbumsListResponse of(List<Album>albumList, String message){
        return new AlbumsListResponse(albumList,message);
    }

    public String getMessage() {
        return message;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }
}
