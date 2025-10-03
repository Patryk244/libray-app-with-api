package com.libray_app_with_api.mapper;

import com.libray_app_with_api.domain.Copy;
import com.libray_app_with_api.domain.dto.CopyDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CopyMapper {
    public static Copy mapToCopy(CopyDto copyDto) {
        return new Copy(
                copyDto.getCopyId(),
                copyDto.getTitle(),
                copyDto.getStatus()
        );
    }

    public static CopyDto mapToCopyDto(Copy copy) {
        return new CopyDto(
                copy.getCopyId(),
                copy.getTitle(),
                copy.getStatus()
        );
    }

    public static List<CopyDto> mapToCopyDtoList(List<Copy> copyList) {
        return copyList.stream()
                .map(CopyMapper::mapToCopyDto)
                .toList();
    }
}