package com.libray_app_with_api.mapper;

import com.libray_app_with_api.domain.Reader;
import com.libray_app_with_api.domain.dto.ReaderDto;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ReaderMapper {
    public static Reader mapToReader(ReaderDto readerDto) {
        return new Reader(
                readerDto.getReaderId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getCreationDate()
        );
    }

    public static ReaderDto mapToReaderDto(Reader reader) {
        return new ReaderDto(
                reader.getReaderId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getCreationDate()
        );
    }

    public static List<ReaderDto>  mapToReaderDtoList(List<Reader> readers) {
        return readers.stream()
                .map(ReaderMapper::mapToReaderDto)
                .toList();
    }
}