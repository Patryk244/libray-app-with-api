package com.library_app_with_api.mapper;

import com.library_app_with_api.domain.Loan;
import com.library_app_with_api.domain.dto.LoanDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoanMapper {
    public static Loan mapToLoan(LoanDto loanDto) {
        return new Loan(
                loanDto.getLoanId(),
                loanDto.getCopy(),
                loanDto.getReader(),
                loanDto.getBorrowedDate(),
                loanDto.getReturnedDate()
        );
    }

    public static LoanDto mapToLoanDto(Loan loan) {
        return new LoanDto(
                loan.getLoanId(),
                loan.getCopy(),
                loan.getReader(),
                loan.getBorrowedDate(),
                loan.getReturnedDate()
        );
    }

    public static List<LoanDto> mapToLoanDtoList(List<Loan> loans) {
        return loans.stream()
                .map(LoanMapper::mapToLoanDto)
                .toList();
    }
}