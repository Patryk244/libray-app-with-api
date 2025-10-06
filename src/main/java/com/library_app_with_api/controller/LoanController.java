package com.library_app_with_api.controller;

import com.library_app_with_api.controller.errorController.exceptions.ReaderHasBorrowedThisTitle;
import com.library_app_with_api.controller.errorController.exceptions.TitleIsNotAccessToBorrow;
import com.library_app_with_api.domain.*;
import com.library_app_with_api.domain.dto.LoanDto;
import com.library_app_with_api.domain.enums.StatusCopy;
import com.library_app_with_api.mapper.LoanMapper;
import com.library_app_with_api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/loans")
public class LoanController {

    private final ReaderDbService readerDbService;
    private final CopyDbService copyDbService;
    private final LoanDbService loanDbService;

    @GetMapping
    public List<LoanDto> findAll() {
        return LoanMapper.mapToLoanDtoList(loanDbService.displayAllBorrowedAndLoans());
    }

    @PostMapping(value = "/create/borrow/reader/id/{readerId}/title/{title}/author/{author}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<LoanDto> createLoan(@PathVariable Long readerId, @PathVariable String title, @PathVariable String author) throws TitleIsNotAccessToBorrow {
        if (!loanDbService.bookIsBorrowedByUser(title, author, readerId)) {
            Reader reader = readerDbService.findById(readerId).get();
            Copy copy = copyDbService.findFirstAvailableCopyByTitleAndAuthor(title, author);
            Loan loan = LoanMapper.mapToLoan(new LoanDto(
                    null,
                    copy,
                    reader,
                    LocalDate.now(),
                    null
            ));
            copyDbService.updateStatusCopyByCopyId(StatusCopy.BORROWED, copy.getCopyId());
            Loan toSaved = loanDbService.borrowBookAndLoan(loan);
            return ResponseEntity.ok(LoanMapper.mapToLoanDto(toSaved));
        } else {
            throw new ReaderHasBorrowedThisTitle();
        }
    }
    @PutMapping(value = "/reader/{readerId}/return/title/{title}/author/{author}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<LoanDto> returnTitle(@PathVariable Long readerId, @PathVariable String title, @PathVariable String author) {
        Loan found = loanDbService.titleToReturn(readerId, title, author).get(0);
        Loan toSaved = LoanMapper.mapToLoan(new LoanDto(
                found.getLoanId(),
                found.getCopy(),
                found.getReader(),
                found.getBorrowedDate(),
                LocalDate.now()
        ));
        loanDbService.borrowBookAndLoan(toSaved);
        copyDbService.updateStatusCopyByCopyId(StatusCopy.IN_CIRCULATION, toSaved.getCopy().getCopyId());
        return ResponseEntity.ok(LoanMapper.mapToLoanDto(toSaved));
    }
}