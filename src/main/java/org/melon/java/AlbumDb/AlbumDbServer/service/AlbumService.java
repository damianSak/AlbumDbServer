package org.melon.java.AlbumDb.AlbumDbServer.service;

import org.melon.java.AlbumDb.AlbumDbServer.model.Album;
import org.melon.java.AlbumDb.AlbumDbServer.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> findAll() {
        return (List<Album>) albumRepository.findAll();
    }

    public Album findById(int id) {
        Optional<Album> result = albumRepository.findById(id);
        return result.orElse(null);
    }

    public List<Album> listFindById(int id) {
        List<Album> result = new ArrayList<>();
        Album album = findById(id);
        result.add(album);
        if(album ==null){
            result.clear();
        }
        return result;
    }

    public List<Album> findByBand(String band) {
        return albumRepository.findByBand(band);
    }

    public List<Album> findByTitle(String title) {
        return albumRepository.findByTitle(title);
    }

    public List<Album> findByReleaseYear(int releaseYear) {
        return albumRepository.findByReleaseYear(releaseYear);
    }

    public List<Album> findByGenre(String genre) {
        return albumRepository.findByGenre(genre);
    }

    public Optional<Album> findByTitleAndBand(String title, String band) {
        return albumRepository.findByTitleAndBand(title, band);
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public Album addAlbum(Album album) {
        return save(album);
    }

    public Album delete(int id) {
        Album album = findById(id);
        albumRepository.deleteById(id);
        return album;
    }

    public Album updateWholeRecord(Album album, int id) {

        Album albumToUpdate = listFindById(id).get(0);
        albumToUpdate.setTitle(album.getTitle());
        albumToUpdate.setBand(album.getBand());
        albumToUpdate.setGenre(album.getGenre());
        albumToUpdate.setReleaseYear(album.getReleaseYear());
        return save(albumToUpdate);
    }
}
