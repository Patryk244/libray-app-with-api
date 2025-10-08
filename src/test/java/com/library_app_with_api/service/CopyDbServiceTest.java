package com.library_app_with_api.service;

import com.library_app_with_api.controller.errorController.exceptions.TitleIsNotAccessToBorrow;
import com.library_app_with_api.domain.*;
import com.library_app_with_api.domain.enums.StatusCopy;
import com.library_app_with_api.repository.CopyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Year;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CopyDbServiceTest {

    @Mock
    private CopyRepository copyRepository;

    @InjectMocks
    private CopyDbService copyDbService;

    @Test
    void shouldSaveCopy() {
        Title savedTitle = new Title(1L, "Rok 1984", "George Orwell", Year.of(1949));
        Copy copy = new Copy(null, savedTitle, StatusCopy.IN_CIRCULATION);
        Copy savedCopy = new Copy(1L, savedTitle, StatusCopy.IN_CIRCULATION);

        when(copyRepository.save(copy)).thenReturn(savedCopy);

        Copy result = copyDbService.save(copy);
        assertEquals(1L, result.getCopyId());
        verify(copyRepository, times(1)).save(copy);
    }

    @Test
    void shouldCountByTitle() {
        String title = "Rok 1984";
        when(copyRepository.findByTitleAndCount(title)).thenReturn(5L);

        long result = copyDbService.findByTitleAndCount(title);
        assertEquals(5L, result);
    }

    @Test
    void shouldFindById() {
        Copy copy = new Copy(1L, null, StatusCopy.IN_CIRCULATION);

        when(copyRepository.findById(copy.getCopyId())).thenReturn(Optional.of(copy));

        Optional<Copy> result = copyDbService.findCopyById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void shouldFindAll() {
        Copy copy = new Copy(1L, null, StatusCopy.IN_CIRCULATION);
        Copy copy2 = new Copy(2L, null, StatusCopy.IN_CIRCULATION);
        List<Copy> copyList = Arrays.asList(copy, copy2);

        when(copyRepository.findAll()).thenReturn(copyList);

        List<Copy> result = copyDbService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void shouldFindByTitleAndAuthor() {
        Title titleOb = new Title(1L, "Rok 1984", "George Orwell", Year.of(1949));
        Copy copy1 = new Copy(1L, titleOb, StatusCopy.IN_CIRCULATION);
        Copy copy2 = new Copy(2L, titleOb, StatusCopy.BORROWED);
        List<Copy> copyList = Arrays.asList(copy1, copy2);
        when(copyRepository.findCopyByTitleAndAuthor("Rok 1984",  "George Orwell")).thenReturn(copyList);
        List<Copy> result = copyDbService.findCopyByTitleAndAuthor("Rok 1984", "George Orwell");
        assertEquals(2, result.size());
    }

    @Test
    void shouldFindFirstAvailableCopy() throws TitleIsNotAccessToBorrow {
        Title titleOb = new Title(1L, "Rok 1984", "George Orwell", Year.of(1949));
        Copy copy1 = new Copy(1L, titleOb, StatusCopy.IN_CIRCULATION);

        when(copyRepository.findFirstAvailableCopyByTitleAndAuthor("Rok 1984", "George Orwell")).thenReturn(copy1);

        Copy result = copyDbService.findFirstAvailableCopyByTitleAndAuthor("Rok 1984", "George Orwell");

        assertNotNull(result);
        assertEquals(copy1, result);
        assertDoesNotThrow(() -> copyDbService.findFirstAvailableCopyByTitleAndAuthor("Rok 1984", "George Orwell"));
    }

}