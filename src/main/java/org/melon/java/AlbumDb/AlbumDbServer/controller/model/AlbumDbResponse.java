package org.melon.java.AlbumDb.AlbumDbServer.controller.model;

import org.melon.java.AlbumDb.AlbumDbServer.model.Album;

import java.util.List;

public class AlbumDbResponse {

    private String message;
    private Album album;
    private List<Album> albumList;

    private AlbumDbResponse(Album album , String message) {
        this.message = message;
        this.album = album;
    }

    private AlbumDbResponse(String message) {
        this.message = message;
    }

    private AlbumDbResponse (List<Album> albumList,String message) {
        this.message = message;
        this.albumList = albumList;
    }

    public static AlbumDbResponse of(Album album, String message) {
        return new AlbumDbResponse(album, message);
    }

    public static AlbumDbResponse of(String message){
        return new AlbumDbResponse( message);
    }

    public static AlbumDbResponse of(List<Album> albumList, String message){
        return new AlbumDbResponse(albumList, message);
    }

    public String getMessage() {
        return message;
    }

    public Album getAlbum() {
        return album;
    }
}
