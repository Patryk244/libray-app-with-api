package com.libray_app_with_api.domain.dto;

import jakarta.annotation.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReaderDto {
    @Nullable
    private Long readerId;
    @NotEmpty(message = "Your firstname is empty")
    @Size(min = 3, max = 20, message = "Your firstname must contains from 3 to 20 characters.")
    private String firstName;
    @NotEmpty(message = "Your lastname is empty")
    @Size(min = 3, max = 20, message = "Your lastname must contains from 3 to 20 characters.")
    private String lastName;
    @FutureOrPresent
    private LocalDate creationDate;
}
