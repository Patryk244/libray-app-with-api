package com.libray_app_with_api.domain.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.Year;

@Getter
@AllArgsConstructor
public class TitleDto {
    private Long titleId;

    @Size(min = 6, max = 30, message = "Your title must contains from 6 to 30 characters.")
    private String title;
    @Size(min = 7, max = 40, message = "Your author must contains from 7 to 40 characters.")
    private String author;

    @PastOrPresent(message = "Your date release must be in the past or present!")
    private Year dateRelease;
}