package com.libray_app_with_api.domain.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private Long readerId;

    @Size(min = 3, max = 20, message = "Your firstname must contains from 3 to 20 characters.")
    private String firstName;

    @Size(min = 3, max = 20, message = "Your lastname must contains from 3 to 20 characters.")
    private String lastName;

    @FutureOrPresent
    private LocalDate creationDate;
}
