package com.library_app_with_api.service;

import com.library_app_with_api.controller.errorController.exceptions.TitleNotFound;
import com.library_app_with_api.domain.Title;
import com.library_app_with_api.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.Year;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TitleDbServiceTest {

    @Mock
    private TitleRepository titleRepository;

    @InjectMocks
    private TitleDbService titleDbService;

    @Test
    void shouldSaveTitle() {
        Title title = new Title(null, "Rok 1984", "George Orwell", Year.of(1949));
        Title savedTitle = new Title(1L, "Rok 1984", "George Orwell", Year.of(1949));

        when(titleRepository.save(title)).thenReturn(savedTitle);

        Title result = titleDbService.save(title);
        assertEquals(1L, result.getTitleId());
    }

    @Test
    void shouldFindAllTitles() {
        Title title = new Title(1L, "Rok 1984", "George Orwell", Year.of(1949));
        Title title2 = new Title(2L, "Zbrodnia i kara", "Fiodor Dostojewski", Year.of(1866));
        List<Title> titles = List.of(title, title2);

        when(titleRepository.findAll()).thenReturn(titles);

        List<Title> result = titleDbService.findAll();
        assertEquals(result.size(), 2);
    }

    @Test
    void shouldFindTitleById() {
        Title title = new Title(1L, "Rok 1984", "George Orwell", Year.of(1949));

        when(titleRepository.findById(title.getTitleId())).thenReturn(Optional.of(title));

        Optional<Title> result = titleDbService.findTitleById(1L);

        assertTrue(result.isPresent());
        assertDoesNotThrow(() -> titleDbService.findTitleById(1L));
    }

    @Test
    void shouldFindTitleByIdIsNotPresent() {
        Long titleId = 1L;

        when(titleRepository.findById(titleId)).thenReturn(Optional.empty());

        assertThrows(TitleNotFound.class, () -> titleDbService.findTitleById(titleId));
    }

    @Test
    void shouldFindByAuthorAndTitle() {
        String author = "George Orwell";
        String title = "Rok 1984";
        Title titleToCheck = new Title(1L, "Rok 1984", "George Orwell", Year.of(1949));
        when(titleRepository.findTitleByTitleAndAuthor(titleToCheck.getTitle(), titleToCheck.getAuthor())).thenReturn(Optional.of(titleToCheck));
        Optional<Title> result = titleDbService.findTitleByTitleAndAuthor(title, author);
        assertTrue(result.isPresent());
    }

    @Test
    void shouldFindByAuthorAndTitleNot() {
        String author = "not exists";
        String title = "not exists";

        when(titleRepository.findTitleByTitleAndAuthor(title, author)).thenReturn(Optional.empty());
        assertThrows(TitleNotFound.class, () -> titleDbService.findTitleByTitleAndAuthor(title, author));
    }
}