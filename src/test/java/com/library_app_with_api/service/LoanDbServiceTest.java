package com.library_app_with_api.service;

import com.library_app_with_api.controller.errorController.exceptions.LoanNotFound;
import com.library_app_with_api.domain.*;
import com.library_app_with_api.domain.enums.StatusCopy;
import com.library_app_with_api.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanDbServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanDbService loanDbService;

    @Test
    void shouldBorrowBookAndLoan() {
        Reader reader = new Reader(1L, "Patryk", "Nowak", LocalDate.now());
        Title title = new Title(1L, "Rok 1984", "George Orwell", Year.of(1949));
        Copy copy = new Copy(null, title, StatusCopy.IN_CIRCULATION);
        Loan loan =  new Loan(null, copy, reader, LocalDate.now(), null);
        Loan loanSaved =  new Loan(1L, copy, reader, LocalDate.now(), null);

        when(loanRepository.save(loan)).thenReturn(loanSaved);

        Loan result = loanDbService.borrowBookAndLoan(loan);

        assertNotNull(result);
        assertEquals(loanSaved, result);
    }

    @Test
    void shouldFindAll() {
        Loan loan1 = new Loan(1L, null, null, LocalDate.now(), null);
        Loan loan2 = new Loan(1L, null, null, LocalDate.now(), null);
        List<Loan> loans = List.of(loan1, loan2);
        when(loanRepository.findAll()).thenReturn(loans);

        List<Loan> result = loanDbService.displayAllBorrowedAndLoans();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void shouldBookIsBorrowedByUser() {
        when(loanRepository.readerHasBorrowedBook("test book", "test author", 1L)).thenReturn(true);
        boolean result = loanDbService.bookIsBorrowedByUser("test book", "test author", 1L);
        assertTrue(result);
    }

    @Test
    void shouldTitleToReturn() {
        Loan loan = new Loan(1L, null, null, LocalDate.now(), LocalDate.now());
        when(loanRepository.titleToReturn(1L, "title test", "test author")).thenReturn(List.of(loan));

        List<Loan> result = loanDbService.titleToReturn(1L, "title test", "test author");
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void shouldTitleToReturnIsNotFound() {
        when(loanRepository.titleToReturn(1L, "title test", "test author")).thenReturn(List.of());
        assertThrows(LoanNotFound.class, () -> loanDbService.titleToReturn(1L, "title test", "test author"));
    }

}