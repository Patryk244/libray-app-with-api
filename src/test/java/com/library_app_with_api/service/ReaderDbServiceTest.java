package com.library_app_with_api.service;

import com.library_app_with_api.controller.errorController.exceptions.ReaderNotFound;
import com.library_app_with_api.domain.Reader;
import com.library_app_with_api.repository.ReaderRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReaderDbServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @InjectMocks
    private ReaderDbService readerDbService;

    @Test
    void shouldSaveReader() {
        Reader reader = new Reader(null, "Ania", "Kot", LocalDate.now());
        Reader savedReader = new Reader(1L, "Ania", "Kot", LocalDate.now());

        when(readerRepository.save(reader)).thenReturn(savedReader);

        Reader result = readerDbService.save(reader);

        assertNotNull(result.getReaderId());
        assertEquals(1L, result.getReaderId());
        verify(readerRepository, times(1)).save(reader);
    }

    @Test
    void shouldFindReaderById() {
        Reader reader = new Reader(1L, "Ania", "Kot", LocalDate.now());

        when(readerRepository.findById(reader.getReaderId())).thenReturn(Optional.of(reader));

        Optional<Reader> result = readerDbService.findById(1L);

        assertTrue(result.isPresent());
        assertDoesNotThrow(() -> readerDbService.findById(1L));
    }

    @Test
    void shouldFindReaderByIdIsNotPresent() {
        Long readerId = 1L;

        when(readerRepository.findById(readerId)).thenReturn(Optional.empty());

        assertThrows(ReaderNotFound.class, () -> readerDbService.findById(readerId));
    }


    @Test
    void shouldFindAllReaders() {
        Reader reader = new Reader(1L, "Ania", "Kot", LocalDate.now());
        Reader savedReader = new Reader(1L, "Patryk", "Nowak", LocalDate.now());
        List<Reader> readers = List.of(reader ,savedReader);

        when(readerRepository.findAll()).thenReturn(readers);
        List<Reader> result = readerDbService.findAll();
        assertEquals(readers.size(), result.size());
    }
}