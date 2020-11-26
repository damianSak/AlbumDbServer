package org.melon.java.AlbumDb.AlbumDbServer.controller;

import org.melon.java.AlbumDb.AlbumDbServer.model.Album;
import org.melon.java.AlbumDb.AlbumDbServer.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/database")
public class AlbumController {

    private AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/find_all")
    public List<Album> findAll() {
        return albumService.findAll();
    }

    @GetMapping("/find/id/{id}")
    public Album findById(@PathVariable int id) {
        return albumService.findById(id);
    }

    @GetMapping("/find/band/{band}")
    public List<Album> findByBand(@PathVariable String band) {
        return albumService.findByBand(band);
    }

    @GetMapping("/find/title/{title}")
    public List<Album> findByTitle(@PathVariable String title) {
        return albumService.findByTitle(title);
    }

    @GetMapping("/find/releaseYear/{releaseYear}")
    public List<Album> findByReleaseYear(@PathVariable int releaseYear) {
        return albumService.findByReleaseYear(releaseYear);
    }
    @GetMapping("/find/genre/{genre}")
    public List<Album> findByGenre(@PathVariable String genre) {
        return albumService.findByGenre(genre);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addAlbum(@RequestBody Album album) {
        Optional<Album> albumFromDb = albumService.findByTitleAndBand(album.getTitle(),album.getBand());
        if(albumFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        return ResponseEntity.ok(albumService.addAlbum(album)) ;
    }

    @DeleteMapping("/delete_by_id/{id}")
    public void deleteById(@PathVariable int id) {
        albumService.deleteById(id);
    }

    @DeleteMapping("/delete_by_title/{title}")
    public void deleteByTitle(@PathVariable String title) {
        albumService.deleteByTitle(title);
    }

    @PutMapping("/update/{id}")
    public Album updateWholeRecord(@RequestBody Album album, @PathVariable int id) {
        return albumService.updateWholeRecord(album, id);
    }

    @PutMapping("/update_band/{id}")
    public void updateBand(@RequestBody Album album, @PathVariable int id) {
        albumService.updateBand(album.getBand(), id);
    }

    @PutMapping("/update_title/{id}")
    public void updateTitle(@RequestBody Album album, @PathVariable int id) {
        albumService.updateTitle(album.getTitle(), id);
    }

    @PutMapping("/update_year/{id}")
    public void updateReleaseYeare(@RequestBody Album album, @PathVariable int id) {
        albumService.updateReleaseYeare(album.getReleaseYear(), id);
    }

}


