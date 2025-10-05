package com.library_app_with_api.mapper;

import com.library_app_with_api.domain.Title;
import com.library_app_with_api.domain.dto.TitleDto;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TitleMapper {
    public static Title mapToTitle(TitleDto titleDto) {
        return new Title(
                titleDto.getTitleId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getDateRelease()
        );
    }

    public static TitleDto mapToTitleDto(Title title) {
        return new TitleDto(
                title.getTitleId(),
                title.getTitle(),
                title.getAuthor(),
                title.getDateRelease()
        );
    }

    public static List<TitleDto> mapToTitleDtoList(List<Title> titles) {
        return titles.stream()
                .map(TitleMapper::mapToTitleDto)
                .toList();
    }
}