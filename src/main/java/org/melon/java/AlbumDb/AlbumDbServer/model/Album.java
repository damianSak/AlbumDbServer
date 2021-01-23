package org.melon.java.AlbumDb.AlbumDbServer.model;

import javax.persistence.*;

@Entity
@Table(name = "AlbumsCollection")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bandId")
    private int id;

    @Column(name = "bandName", nullable = false)
    private String band;

    @Column(name = "albumTitle", nullable = false)
    private String title;

    @Column(name = "musicGenre", nullable = false)
    private String genre;

    @Column(name = "yearOfRelease", nullable = false)
    private int releaseYear;

    private Album(int id, String band, String title, String genre, int releaseYear) {
        this.id = id;
        this.band = band;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public static AlbumBuilder builder() {
        return new AlbumBuilder();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
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

    public static final class AlbumBuilder {
        private int id;
        private String band;
        private String title;
        private String genre;
        private int releaseYear;

        private AlbumBuilder() {
        }

        public AlbumBuilder id(int id) {
            this.id = id;
            return this;
        }

        public AlbumBuilder band(String band) {
            this.band = band;
            return this;
        }

        public AlbumBuilder title(String title) {
            this.title = title;
            return this;
        }

        public AlbumBuilder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public AlbumBuilder releaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public Album build() {
            return new Album(id, band, title, genre, releaseYear);
        }
    }
}
