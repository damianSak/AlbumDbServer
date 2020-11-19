package org.melon.java.AlbumDb.AlbumDbServer.service;

import org.melon.java.AlbumDb.AlbumDbServer.model.Album;
import org.melon.java.AlbumDb.AlbumDbServer.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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

    public List<Album> findByBand(String band) {
        return albumRepository.findByBand(band);
    }

    public List<Album> findByTitle(String title) {
        return albumRepository.findByTitle(title);
    }

    public List<Album> findByReleaseYear(int releaseYear) {
        return albumRepository.findByReleaseYear(releaseYear);
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public Album addAlbum(Album album) {
        return save(album);
    }

    public void deleteById(int id) {
        albumRepository.deleteById(id);
    }

    public void deleteByTitle(String title) {
        List<Album> albumsToDelete = findByTitle(title);
        if (albumsToDelete.size() == 1) {
            Album albumToDelete = albumsToDelete.get(0);
            albumRepository.deleteById(albumToDelete.getId());
        } else if (albumsToDelete.size() >= 2) {
            for (Album album : albumsToDelete) {
                findById(album.getId());
            }
            Scanner scan = new Scanner(System.in);
            System.out.print("Choose a Id of album to be delete:");
            int id = scan.nextInt();
            deleteById(id);

        } else{

        }
    }

    public Album updateWholeRecord(Album album, int id) {
        if (album.getId() != id) {

        }
        Album albumToUpdate = albumRepository.findById(id).orElseThrow();
        albumToUpdate.setId(album.getId());
        albumToUpdate.setBand(album.getBand());
        albumToUpdate.setGenre(album.getGenre());
        albumToUpdate.setReleaseYear(album.getReleaseYear());
        return save(album);
    }

    public void updateBand(String band, int id) {
        albumRepository.updateBand(band, id);
    }

    public void updateTitle(String title, int id) {
        albumRepository.updateTitle(title, id);
    }

    public void updateReleaseYeare(int releaseYeare, int id) {
        albumRepository.updateReleaseYeare(releaseYeare, id);
    }
}
