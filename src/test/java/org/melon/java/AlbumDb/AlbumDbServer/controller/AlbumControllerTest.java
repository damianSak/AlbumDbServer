package org.melon.java.AlbumDb.AlbumDbServer.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.melon.java.AlbumDb.AlbumDbServer.controller.model.AlbumDbResponse;
import org.melon.java.AlbumDb.AlbumDbServer.controller.model.AlbumsListResponse;
import org.melon.java.AlbumDb.AlbumDbServer.model.Album;
import org.melon.java.AlbumDb.AlbumDbServer.service.AlbumService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumControllerTest {

    @Mock
    AlbumService albumServiceMock;

    @InjectMocks
    AlbumController testObject;

    @Test
    void findAll_should_returnAlbumElementsList_when_repositoryReturnsAlbumList() {
        // given
        Album album1 = Album.builder().band("Tool").build();
        Album album2 = Album.builder().band("Korn").title("Follow the leader").build();
        List<Album> testList = Arrays.asList(album1, album2);

        when(albumServiceMock.findAll()).thenReturn(testList);

        // when
        ResponseEntity<AlbumsListResponse> result = testObject.findAll();

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().get(0).getBand()).isEqualTo(album1.getBand());
        assertThat(result.getBody().getAlbumList().get(1).getTitle()).isEqualTo(album2.getTitle());
        assertThat(result.getBody().getMessage()).isEqualTo("Wyszukano następujące albumy w bazie danych:");
    }

    @Test
    void findAll_should_returnEmptyList_when_repositoryReturnNothing() {
        // given
        List<Album> testList = new ArrayList<>();

        when(albumServiceMock.findAll()).thenReturn(testList);

        // when
        ResponseEntity<AlbumsListResponse> result = testObject.findAll();

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(0);
        assertThat(result.getBody().getMessage()).isEqualTo("Wyszukano następujące albumy w bazie danych:");
    }

    @Test
    void findById_should_returnAlbumList_when_properIdIsGiven() {
        // given
        Album testAlbum = Album.builder().id(1).build();
        List<Album> testList = Arrays.asList(testAlbum);

        when(albumServiceMock.listFindById(1)).thenReturn(testList);
        //when
        ResponseEntity<AlbumsListResponse> result = testObject.findById(1);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(1);
        assertThat(result.getBody().getMessage()).isEqualTo("Znaleziono następujący album:");
    }

    @Test
    void findById_should_returnEmptyList_when_wrongIdIsGiven() {
        // given
        List<Album> testList = new ArrayList<>();

        when(albumServiceMock.listFindById(1)).thenReturn(testList);

        //when
        ResponseEntity<AlbumsListResponse> result = testObject.findById(1);

        //then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(0);
        assertThat(result.getBody().getMessage()).isEqualTo("Znaleziono następujący album:");
    }

    @Test
    void findByBand_should_returnAlbumElementsList_when_properBandIsGiven() {
        // given
        Album album1 = Album.builder().band("Seether").build();
        Album album2 = Album.builder().band("Seether").build();
        List<Album> testList = Arrays.asList(album1, album2);

        when(albumServiceMock.findByBand("Seether")).thenReturn(testList);

        // when
        ResponseEntity<AlbumsListResponse> result = testObject.findByBand("Seether");

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(2);
        assertThat(result.getBody().getAlbumList().get(0).getBand()).isEqualTo(album1.getBand());
        assertThat(result.getBody().getAlbumList().get(1).getBand()).isEqualTo(album2.getBand());
        assertThat(result.getBody().getMessage()).isEqualTo("Wyszukano następujące albumy podanego wykonawcy:");
    }

    @Test
    void findByBand_should_returnEmptyList_when_wrongBandIsGiven() {
        // given
        List<Album> testList = new ArrayList<>();

        when(albumServiceMock.findByBand("Seether")).thenReturn(testList);

        // when
        ResponseEntity<AlbumsListResponse> result = testObject.findByBand("Seether");

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(0);
        assertThat(result.getBody().getMessage()).isEqualTo("Wyszukano następujące albumy podanego wykonawcy:");
    }

    @Test
    void findByTitle_should_returnAlbumElementsList_when_properTitleIsGiven() {
        // given
        Album album1 = Album.builder().band("Seether").title("test").build();
        Album album2 = Album.builder().band("Nine inch nails").title("test").build();
        List<Album> testList = Arrays.asList(album1, album2);

        when(albumServiceMock.findByTitle("test")).thenReturn(testList);

        // when
        ResponseEntity<AlbumsListResponse> result = testObject.findByTitle("test");

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(2);
        assertThat(result.getBody().getAlbumList().get(0).getTitle()).isEqualTo(album1.getTitle());
        assertThat(result.getBody().getAlbumList().get(1).getBand()).isEqualTo(album2.getBand());
        assertThat(result.getBody().getMessage()).isEqualTo("Wyszukano następujące albumy z podanym tytułem:");
    }

    @Test
    void findByTitle_should_returnEmptyList_when_wrongTitleIsGiven() {
        // given
        List<Album> testList = new ArrayList<>();

        when(albumServiceMock.findByTitle("test")).thenReturn(testList);

        // when
        ResponseEntity<AlbumsListResponse> result = testObject.findByTitle("test");

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(0);
        assertThat(result.getBody().getMessage()).isEqualTo("Wyszukano następujące albumy z podanym tytułem:");
    }

    @Test
    void findByReleaseYear_should_returnAlbumElementsList_when_properReleaseYearIsGiven() {
        // given
        Album album1 = Album.builder().band("Seether").title("test").releaseYear(2000).build();
        Album album2 = Album.builder().band("Nine inch nails").title("test").releaseYear(2000).build();
        List<Album> testList = Arrays.asList(album1, album2);

        when(albumServiceMock.findByReleaseYear(2000)).thenReturn(testList);

        // when
        ResponseEntity<AlbumsListResponse> result = testObject.findByReleaseYear(2000);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(2);
        assertThat(result.getBody().getAlbumList().get(0).getTitle()).isEqualTo(album1.getTitle());
        assertThat(result.getBody().getAlbumList().get(1).getReleaseYear()).isEqualTo(album2.getReleaseYear());
        assertThat(result.getBody().getMessage()).isEqualTo("Wyszukano następujące albumy z podanego roku:");
    }

    @Test
    void findByReleaseYear_should_returnEmptyList_when_wrongReleaseYearIsGiven() {
        // given
        List<Album> testList = new ArrayList<>();

        when(albumServiceMock.findByReleaseYear(2000)).thenReturn(testList);

        // when
        ResponseEntity<AlbumsListResponse> result = testObject.findByReleaseYear(2000);

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(0);
        assertThat(result.getBody().getMessage()).isEqualTo("Wyszukano następujące albumy z podanego roku:");
    }

    @Test
    void findByGenre_should_returnAlbumElementsList_when_properGenreIsGiven() {
        // given
        Album album1 = Album.builder().band("Seether").title("test").releaseYear(2000).genre("Rock").build();
        Album album2 = Album.builder().band("Nine inch nails").title("test").releaseYear(2000).genre("Rock").build();
        List<Album> testList = Arrays.asList(album1, album2);

        when(albumServiceMock.findByGenre("Rock")).thenReturn(testList);

        // when
        ResponseEntity<AlbumsListResponse> result = testObject.findByGenre("Rock");

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(2);
        assertThat(result.getBody().getAlbumList().get(0).getTitle()).isEqualTo(album1.getTitle());
        assertThat(result.getBody().getAlbumList().get(1).getGenre()).isEqualTo(album2.getGenre());
        assertThat(result.getBody().getMessage()).isEqualTo("Wyszukano następujące albumy dla podanego gatunku muzyki:");
    }

    @Test
    void findByGenre_should_returnEmptyList_when_wrongGenreIsGiven() {
        // given
        List<Album> testList = new ArrayList<>();

        when(albumServiceMock.findByGenre("Rock")).thenReturn(testList);

        // when
        ResponseEntity<AlbumsListResponse> result = testObject.findByGenre("Rock");

        // then
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().getAlbumList().size()).isEqualTo(0);
        assertThat(result.getBody().getMessage()).isEqualTo("Wyszukano następujące albumy dla podanego gatunku muzyki:");
    }

    @Test
    void addAlbum_shouldNot_addAlbumToDB_when_givenAlbumIsAlreadyInDB() {
        //given
        Album testAlbum = Album.builder().band("Katatonia").title("Viva emptiness").build();

        when(albumServiceMock.findByTitleAndBand("Viva emptiness", "Katatonia")).
                thenReturn(java.util.Optional.of(testAlbum));

        //when
        ResponseEntity<AlbumDbResponse> response = testObject.addAlbum(testAlbum);

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(422);
        assertThat(response.getBody().getAlbum().getBand()).isEqualTo("Katatonia");
        assertThat(response.getBody().getMessage()).isEqualTo("Wprowadzony album znajduje się juz bazie zapisanych albumów");
    }

    @Test
    void addAlbum_should_addAlbumToDB_when_properAlbumGiven() {
        //given
        Album testAlbum = Album.builder().band("Katatonia").title("Viva emptiness").build();

        when(albumServiceMock.addAlbum(testAlbum)).thenReturn(testAlbum);

        //when
        ResponseEntity<AlbumDbResponse> response = testObject.addAlbum(testAlbum);

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getAlbum().getBand()).isEqualTo("Katatonia");
        assertThat(response.getBody().getMessage()).isEqualTo("Do bazy dodano nową pozycję :");
    }

    @Test
    void delete_should_deleteAlbumFromDB_when_properIdIsGiven() {
        //given
        Album testAlbum = Album.builder().band("Katatonia").title("Viva emptiness").id(1).build();

        when(albumServiceMock.findById(1)).thenReturn(testAlbum);
        when(albumServiceMock.delete(testAlbum.getId())).thenReturn(testAlbum);

        //when
        ResponseEntity<AlbumDbResponse> response = testObject.delete(1);

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getAlbum().getBand()).isEqualTo("Katatonia");
        assertThat(response.getBody().getMessage()).isEqualTo("Z bazy danych usunięto album o ID = " + testAlbum.getId());
    }

    @Test
    void delete_shouldNot_deleteAlbumFromDB_when_wrongIdIsGiven() {
        //given
        Album testAlbum = Album.builder().band("Katatonia").title("Viva emptiness").id(1).build();

        when(albumServiceMock.findById(2)).thenReturn(isNull());

        //when
        ResponseEntity<AlbumDbResponse> response = testObject.delete(2);

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(422);
        assertThat(response.getBody().getMessage()).isEqualTo("Nie odnaleziono albumu o ID = " + 2);
    }


    @Test
    void updateWholeRecord_should_updateAlbumFromDB_when_properIdIsGiven() {
        //given
        Album oldAlbum = Album.builder().band("Katatonia").title("Viva emptiness").id(1).build();
        Album newAlbum = Album.builder().band("Anathema").title("Judgement").id(2).build();

        when(albumServiceMock.findById(1)).thenReturn(oldAlbum);
        when(albumServiceMock.updateWholeRecord(newAlbum, 1)).thenReturn(newAlbum);

        //when
        ResponseEntity<AlbumDbResponse> response = testObject.updateWholeRecord(newAlbum,1);

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getAlbum().getBand()).isEqualTo("Anathema");
        assertThat(response.getBody().getMessage()).isEqualTo("Zaktualizowano cały rekord dotyczacy albumu:");
    }

    @Test
    void updateWholeRecord_shouldNot_updateAlbumFromDB_when_wrongIdIsGiven() {
        //given

        Album newAlbum = Album.builder().band("Anathema").title("Judgement").id(2).build();

        when(albumServiceMock.findById(3)).thenReturn(isNull());


        //when
        ResponseEntity<AlbumDbResponse> response = testObject.updateWholeRecord(newAlbum,3);

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(422);
        assertThat(response.getBody().getAlbum().getBand()).isEqualTo("Anathema");
        assertThat(response.getBody().getMessage()).isEqualTo("Nie znaleziono albumu o podanym ID = " + 3);
    }
}