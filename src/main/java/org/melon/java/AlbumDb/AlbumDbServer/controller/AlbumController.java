package org.melon.java.AlbumDb.AlbumDbServer.controller;


import org.melon.java.AlbumDb.AlbumDbServer.controller.model.AlbumDbResponse;
import org.melon.java.AlbumDb.AlbumDbServer.controller.model.AlbumsListResponse;
import org.melon.java.AlbumDb.AlbumDbServer.model.Album;
import org.melon.java.AlbumDb.AlbumDbServer.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AlbumController {

    private AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/find_all")
    public ResponseEntity<AlbumsListResponse> findAll() {
        return ResponseEntity.ok(AlbumsListResponse.of(albumService.findAll(),
                "Wyszukano następujące albumy w bazie danych:"));
    }

    @GetMapping("/find/id/{id}")
    public ResponseEntity<AlbumsListResponse> findById(@PathVariable int id) {
        return ResponseEntity.ok(AlbumsListResponse.of( albumService.listFindById(id),
                "Znaleziono następujący album:"));
    }

    @GetMapping("/find/band/{band}")
    public ResponseEntity<AlbumsListResponse> findByBand(@PathVariable String band) {
        return ResponseEntity.ok(AlbumsListResponse.of(albumService.findByBand(band),
                "Wyszukano następujące albumy podanego wykonawcy:"));
    }

    @GetMapping("/find/title/{title}")
    public ResponseEntity<AlbumsListResponse> findByTitle(@PathVariable String title) {
        return ResponseEntity.ok(AlbumsListResponse.of(albumService.findByTitle(title),
                "Wyszukano następujące albumy z podanym tytułem:"));
    }

    @GetMapping("/find/releaseYear/{releaseYear}")
    public ResponseEntity<AlbumsListResponse> findByReleaseYear(@PathVariable int releaseYear) {
        return ResponseEntity.ok(AlbumsListResponse.of(albumService.findByReleaseYear(releaseYear),
                "Wyszukano następujące albumy z podanego roku:"));
    }

    @GetMapping("/find/genre/{genre}")
    public ResponseEntity<AlbumsListResponse> findByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(AlbumsListResponse.of(albumService.findByGenre(genre),
                "Wyszukano następujące albumy dla podanego gatunku muzyki:"));
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AlbumDbResponse> addAlbum(@RequestBody Album album) {
        Optional<Album> albumFromDb = albumService.findByTitleAndBand(album.getTitle(), album.getBand());
        if (albumFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(AlbumDbResponse.of(album, "Wprowadzony album " +
                    "znajduje się juz bazie zapisanych albumów"));
        } else {
            return ResponseEntity.ok(AlbumDbResponse.of(albumService.addAlbum(album), "Do bazy dodano nową pozycję :"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AlbumDbResponse> delete(@PathVariable int id) {
        Optional<Album> albumFromDb = Optional.ofNullable(albumService.findById(id));
        if (albumFromDb.isPresent()) {
            Album album = albumService.delete(id);
            return ResponseEntity.ok(AlbumDbResponse.of(album, "Z bazy danych usunięto album o ID = " + id));

        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(AlbumDbResponse.of("Nie odnaleziono albumu o ID = " + id));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AlbumDbResponse> updateWholeRecord(@RequestBody Album album, @PathVariable int id) {
        Optional<Album> albumFromDb = Optional.ofNullable(albumService.findById(id));
        if (albumFromDb.isPresent()) {
            return ResponseEntity.ok(AlbumDbResponse.of(albumService.updateWholeRecord(album, id), "Zaktualizowano cały rekord dotyczacy albumu:"));
        } else {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(AlbumDbResponse.of(album, "Nie znaleziono albumu o podanym ID = " + id));
        }
    }




}


