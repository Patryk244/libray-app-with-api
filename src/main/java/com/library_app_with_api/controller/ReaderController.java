package com.library_app_with_api.controller;

import com.library_app_with_api.controller.controllerService.RequiredByController;
import com.library_app_with_api.domain.Reader;
import com.library_app_with_api.domain.dto.ReaderDto;
import com.library_app_with_api.mapper.ReaderMapper;
import com.library_app_with_api.service.ReaderDbService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/v1/readers")
@RequiredArgsConstructor
public class ReaderController implements RequiredByController<ReaderDto> {

    @Autowired
    private ReaderDbService readerDbService;

    @Override
    @GetMapping
    public List<ReaderDto> findAll() {
        return ReaderMapper.mapToReaderDtoList(readerDbService.findAll());
    }

    @GetMapping(value = "/find/readerId/{id}")
    public Optional<Reader> findById(@PathVariable long id) {
        return readerDbService.findById(id);
    }

    @Override
    @PostMapping(value = "/create/reader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReaderDto> create(@Valid @RequestBody ReaderDto readerDto) {
        Reader reader = ReaderMapper.mapToReader(new ReaderDto(
                null,
                readerDto.getFirstName(),
                readerDto.getLastName(),
                LocalDate.now()
        ));
        Reader savedReader = readerDbService.save(reader);
        return ResponseEntity.ok(ReaderMapper.mapToReaderDto(savedReader));
    }
}