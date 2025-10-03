package com.libray_app_with_api.domain.dto;

import com.libray_app_with_api.domain.Title;
import com.libray_app_with_api.domain.enums.StatusCopy;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@AllArgsConstructor
public class CopyDto {
    private Long copyId;
    private Title title;
    @NotNull(message = "Your status not be null!")
    private StatusCopy status;
}