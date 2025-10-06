package com.library_app_with_api.service.serviceInterface;

import com.library_app_with_api.domain.Loan;
import com.library_app_with_api.domain.dto.LoanDto;
import java.util.*;

public interface DbServicePatternForLoan {
    List<Loan> displayAllBorrowedAndLoans();
    Loan borrowBookAndLoan(Loan loan);
    boolean bookIsBorrowedByUser(String Title,String author, Long userId);
}
