package com.library_app_with_api.domain.dto;

import com.library_app_with_api.domain.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LoanDto {
    private Long loanId;
    @NotNull(message = "Copy must be defined!")
    private Copy copy;
    @NotNull(message = "Reader must be defined!")
    private Reader reader;
    @NotNull(message = "Borrowed time must be defined!")
    private LocalDate borrowedDate;
    private LocalDate returnedDate;
}