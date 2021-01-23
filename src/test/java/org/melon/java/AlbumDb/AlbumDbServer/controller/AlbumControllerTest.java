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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumControllerTest {

    @Mock
    AlbumService albumServiceMock;

    @InjectMocks
    AlbumController testObject;

    @Test
    void findAll_should_returnAlbumElements_when_repositoryReturnsAlbumList() {
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
    }

    @Test
    void findById() {
    }

    @Test
    void findByBand() {
    }

    @Test
    void findByTitle() {
    }

    @Test
    void findByReleaseYear() {
    }

    @Test
    void findByGenre() {
    }

    @Test
    void addAlbum_should_AddAlbumToDB_when_properAlbumIsGiven() {
        //given
        Album testAlbum = Album.builder().band("Katatonia").title("Viva emptiness").build();
        //MockHttpServletRequest request = new MockHttpServletRequest();
        //RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(albumServiceMock.addAlbum(any(Album.class))).thenReturn(testAlbum);

        //when
        ResponseEntity<AlbumDbResponse> response = testObject.addAlbum(testAlbum);

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getAlbum().getBand()).isEqualTo("Katatonia");
        assertThat(response.getBody().getMessage()).isEqualTo("Do bazy dodano nową pozycję :");
    }

    @Test
    void delete() {
    }

    @Test
    void updateWholeRecord() {
    }
}