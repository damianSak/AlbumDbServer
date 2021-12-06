package org.melon.java.AlbumDb.AlbumDbServer.repository;

import org.melon.java.AlbumDb.AlbumDbServer.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> {

    List<Album> findByBand(String band);

    List<Album> findByTitle(String title);

    List<Album> findByReleaseYear(int releaseYear);

    List<Album> findByGenre(String Genre);

    Optional<Album> findByTitleAndBand(String title,String band);



}
