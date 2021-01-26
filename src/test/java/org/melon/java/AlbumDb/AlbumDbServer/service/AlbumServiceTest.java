package org.melon.java.AlbumDb.AlbumDbServer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.melon.java.AlbumDb.AlbumDbServer.model.Album;
import org.melon.java.AlbumDb.AlbumDbServer.repository.AlbumRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    @Mock
    AlbumRepository albumRepositoryMock;

    @InjectMocks
    AlbumService testObject;

    @Test
    void findAll_should_returnAlbumElements_when_repositoryReturnsAlbumList() {
        // given
        Album album1 = Album.builder().band("Tool").build();
        Album album2 = Album.builder().band("Korn").title("Follow the leader").build();
        List<Album> testList = Arrays.asList(album1, album2);

        when(albumRepositoryMock.findAll()).thenReturn(testList);

        // when
        List<Album> result = testObject.findAll();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getBand()).isEqualTo(album1.getBand());
        assertThat(result.get(1).getBand()).isEqualTo(album2.getBand());
        assertThat(result.get(1).getTitle()).isEqualTo(album2.getTitle());
    }

    @Test
    void findAll_shouldNot_returnAlbumElements_when_repositoryReturnsEmptyList() {
        // given
        List<Album> testList = new ArrayList<>();
        when(albumRepositoryMock.findAll()).thenReturn(testList);

        // when
        List<Album> result = testObject.findAll();

        // then
        assertThat(result).hasSize(0);
    }

    @Test
    void findById_should_returnAlbum_when_repositoryReturnProperAlbum() {
        // given
        Album testAlbum = Album.builder().band("In Flames").title("Clayman").id(1).build();
        when(albumRepositoryMock.findById(1)).thenReturn(java.util.Optional.of(testAlbum));

        //when
        Album result = testObject.findById(1);

        //then
        assertThat(result).isEqualTo(testAlbum);
        assertThat(result.getTitle()).isEqualTo("Clayman");
    }

    @Test
    void findById_should_returnNull_when_repositoryReturnsNothing() {
        // given
        Album testAlbum = null;
        when(albumRepositoryMock.findById(1)).thenReturn(java.util.Optional.ofNullable(testAlbum));

        // when
        Album result = testObject.findById(1);

        // then
        assertThat(result).isEqualTo(null);
    }

    @Test
    void listFindById_should_returnAlbumElements_when_repositoryReturnsAlbumList() {
        // given
        Album album1 = Album.builder().band("Korn").title("Follow the leader").id(1).build();
        List<Album> testList = Arrays.asList(album1);
        Album testAlbum = testList.get(0);

        when(albumRepositoryMock.findById(1)).thenReturn(java.util.Optional.ofNullable(testAlbum));

        // when
        List<Album> result = testObject.listFindById(1);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getBand()).isEqualTo(album1.getBand());
        assertThat(result.get(0).getId()).isEqualTo(1);
    }

    @Test
    void listFindById_should_returnNothing_when_repositoryReturnsNothing() {
        // given
        Album album1 = null;
        List<Album> testList = Arrays.asList(album1);
        Album testAlbum = testList.get(0);

        when(albumRepositoryMock.findById(1)).thenReturn(java.util.Optional.ofNullable(testAlbum));

        // when
        List<Album> result = testObject.listFindById(1);

        // then
        assertThat(result).hasSize(0);
    }

    @Test
    void findByBand_should_returnAlbumElementsList_when_repositoryReturnProperAlbumList() {
        //given
        Album album1 = Album.builder().band("Korn").title("Untouchables").id(1).genre("Nu metal").releaseYear(2002).build();
        Album album2 = Album.builder().band("Korn").title("Follow the leader").id(2).genre("Nu metal").releaseYear(1998).build();
        Album album3 = Album.builder().band("Korn").title("Issues").id(3).genre("Nu metal").releaseYear(2002).build();
        List<Album> testList = Arrays.asList(album1, album2, album3);

        when(albumRepositoryMock.findByBand("Korn")).thenReturn(testList);

        //when
        List<Album> result = testObject.findByBand("Korn");

        //then
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getTitle()).isEqualTo("Untouchables");
        assertThat(result.get(2).getId()).isEqualTo(3);
    }

    @Test
    void findByTitle_should_returnAlbumElementsList_when_repositoryReturnProperAlbumList() {
        //given
        Album album1 = Album.builder().band("Korn").title("Untouchables").id(1).genre("Nu metal").releaseYear(2002).build();
        Album album2 = Album.builder().band("Moonspell").title("Untouchables").id(3).genre("Nu metal").releaseYear(2002).build();
        List<Album> testList = Arrays.asList(album1, album2);

        when(albumRepositoryMock.findByTitle("Untouchables")).thenReturn(testList);

        //when
        List<Album> result = testObject.findByTitle("Untouchables");

        //then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Untouchables");
        assertThat(result.get(1).getBand()).isEqualTo("Moonspell");
    }

    @Test
    void findByReleaseYear_should_returnAlbumElementsList_when_repositoryReturnProperAlbumList() {
        //given
        Album album1 = Album.builder().band("Korn").title("Untouchables").id(1).genre("Nu metal").releaseYear(2002).build();
        Album album2 = Album.builder().band("Moonspell").title("Untouchables").id(3).genre("Nu metal").releaseYear(2002).build();
        List<Album> testList = Arrays.asList(album1, album2);

        when(albumRepositoryMock.findByReleaseYear(2002)).thenReturn(testList);

        //when
        List<Album> result = testObject.findByReleaseYear(2002);

        //then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getGenre()).isEqualTo("Nu metal");
        assertThat(result.get(1).getBand()).isEqualTo("Moonspell");
    }

    @Test
    void findByGenre_should_returnAlbumElementsList_when_repositoryReturnProperAlbumList() {
        //given
        Album album1 = Album.builder().band("Korn").title("Untouchables").id(1).genre("Nu metal").releaseYear(2007).build();
        Album album2 = Album.builder().band("Slipknot").title("Iowa").id(2).genre("Nu metal").releaseYear(2002).build();
        Album album3 = Album.builder().band("Moonspell").title("Untouchables").id(3).genre("Nu metal").releaseYear(2002).build();
        List<Album> testList = Arrays.asList(album1, album2, album3);

        when(albumRepositoryMock.findByGenre("Nu metal")).thenReturn(testList);

        //when
        List<Album> result = testObject.findByGenre("Nu metal");

        //then
        assertThat(result).hasSize(3);
        assertThat(result.get(0).getReleaseYear()).isEqualTo(2007);
        assertThat(result.get(1).getBand()).isEqualTo("Slipknot");
    }

    @Test
    void findByTitleAndBand_should_returnAlbum_when_repositoryReturnProperAlbum() {
        // given
        Optional<Album> testAlbum = Optional.of(Album.builder().band("Slipknot").title("Iowa").id(1).genre("Nu metal").releaseYear(2002).build());
        when(albumRepositoryMock.findByTitleAndBand("Iowa", "Slipknot")).thenReturn(testAlbum);

        //when
        Optional<Album> result = testObject.findByTitleAndBand("Iowa", "Slipknot");

        //then
        assertThat(result).isEqualTo(testAlbum);
        assertThat(result.get().getBand()).isEqualTo(testAlbum.get().getBand());
        assertThat(result.get().getId()).isEqualTo(1);
    }

    @Test
    void findByTitleAndBand_should_returnNull_when_repositoryReturnsNothing() {
        // given
        Optional<Album> testAlbum = null;
        when(albumRepositoryMock.findByTitleAndBand("Iowa", "Slipknot")).thenReturn(testAlbum);

        // when
        Optional<Album> result = testObject.findByTitleAndBand("Iowa", "Slipknot");

        // then
        assertThat(result).isEqualTo(null);
    }

    @Test
    void save_should_returnAlbum_when_properAlbumIsGivenAndInvokeOneTime() {
        //given
        Album album1 = Album.builder().band("Korn").title("Untouchables").id(1).genre("Nu metal").releaseYear(2007).build();
        when(albumRepositoryMock.save(album1)).thenReturn(album1);

        //when
        Album testAlbum = testObject.save(album1);

        //ten
        verify(albumRepositoryMock, times(1)).save(album1);
        assertThat(testAlbum).isEqualTo(album1);
    }

    @Test
    void addAlbum_should_returnAlbum_when_properAlbumIsGivenAndInvokeSaveOneTime() {
        //given
        Album album1 = Album.builder().band("Korn").title("Untouchables").id(1).genre("Nu metal").releaseYear(2007).build();
        when(albumRepositoryMock.save(album1)).thenReturn(album1);

        //when
        Album testAlbum = testObject.addAlbum(album1);

        //ten
        verify(albumRepositoryMock, times(1)).save(album1);
        assertThat(testAlbum).isEqualTo(album1);
    }


    @Test
    void delete_should_returnAlbum_when_properAlbumIsGivenAndInvokeDeleteMethodOneTime() {
        //given
        Album album1 = Album.builder().band("Korn").title("Untouchables").id(1).genre("Nu metal").releaseYear(2007).build();

        //when
        Album testAlbum = testObject.delete(1);

        //ten
        verify(albumRepositoryMock, times(1)).deleteById(1);
        assertThat(testAlbum).isEqualTo(null);
    }

    @Test
    void updateWholeRecord_should_updateAlbumFields_when_oldAlbumIsFoundAndProperNewIsGiven() {
        //given
        Album oldAlbum = Album.builder().band("Korn").title("Follow the leader").id(1).genre("Nu metal").releaseYear(1998).build();
        Album newAlbum = Album.builder().band("Moonspell").title("The Antidte").id(2).genre("Nu metal").releaseYear(2002).build();

        when(albumRepositoryMock.findById(1)).thenReturn(Optional.of(oldAlbum));
        when(albumRepositoryMock.save(oldAlbum)).thenReturn(newAlbum);

        //when
        Album result = testObject.updateWholeRecord(newAlbum,1 );

        //then
        verify(albumRepositoryMock,times(1)).save(oldAlbum);
        assertThat(result.getBand()).isEqualTo(newAlbum.getBand());
    }

}