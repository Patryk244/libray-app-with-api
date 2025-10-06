package com.library_app_with_api.service;

import com.library_app_with_api.controller.errorController.exceptions.LoanNotFound;
import com.library_app_with_api.domain.Loan;
import com.library_app_with_api.domain.dto.LoanDto;
import com.library_app_with_api.repository.LoanRepository;
import com.library_app_with_api.service.serviceInterface.DbServicePatternForLoan;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoanDbService implements DbServicePatternForLoan {

    private final LoanRepository loanRepository;


    @Override
    public List<Loan> displayAllBorrowedAndLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan borrowBookAndLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public boolean bookIsBorrowedByUser(String Title, String author, Long userId) {
        return loanRepository.readerHasBorrowedBook(Title, author, userId);
    }

    public List<Loan> titleToReturn(Long readerId, String title, String author) throws LoanNotFound {
        List<Loan> titleFoundToReturn = loanRepository.titleToReturn(readerId, title, author);
        if  (titleFoundToReturn.isEmpty()) {
            throw new LoanNotFound();
        }
        return Objects.requireNonNull(titleFoundToReturn);

    }

}
