package org.melon.java.AlbumDb.AlbumDbServer.model;

import javax.persistence.*;

@Entity
@Table(name = "AlbumsCollection")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "bandId")
    private int id;


    @Column(name = "bandName", nullable = false)
    private String band;


    @Column(name = "albumTitle", nullable = false)
    private String title;

    @Column(name = "musicGenre", nullable = false)
    private String genre;

    @Column(name = "yearOfRelease", nullable = false)
    private int releaseYear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBand() {
        return band;
    }

    public Album setBand(String band) {
        this.band = band;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
