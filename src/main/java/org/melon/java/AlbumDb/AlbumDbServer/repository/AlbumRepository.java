package org.melon.java.AlbumDb.AlbumDbServer.repository;

import org.melon.java.AlbumDb.AlbumDbServer.model.Album;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> {

    List<Album> findByBand(String band);

    List<Album> findByTitle(String title);

    List<Album> findByReleaseYear(int releaseYear);

    List<Album> findByGenre(String Genre);

    @Transactional
    @Modifying
    @Query("update Album a set a.band = ?1 where a.id = ?2" )
    void updateBand( String band, int id);

    @Transactional
    @Modifying
    @Query("update Album a set a.title = ?1 where a.id = ?2" )
    void updateTitle( String title, int id);

    @Transactional
    @Modifying
    @Query("update Album a set a.releaseYear = ?1 where a.id = ?2" )
    void updateReleaseYeare( int releaseYear, int id);
}
