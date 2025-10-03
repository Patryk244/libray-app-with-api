package com.libray_app_with_api.controller;

import com.libray_app_with_api.domain.*;
import com.libray_app_with_api.domain.dto.CopyDto;
import com.libray_app_with_api.domain.enums.StatusCopy;
import com.libray_app_with_api.mapper.CopyMapper;
import com.libray_app_with_api.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/v1/copies")
@RequiredArgsConstructor
public class CopyController {

    @Autowired
    private CopyDbService copyDbService;

    @Autowired
    private TitleDbService titleDbService;

    @GetMapping
    public List<?> findAll() {
        return CopyMapper.mapToCopyDtoList(copyDbService.findAll());
    }

    @GetMapping(value = "/count/title/{title}")
    public String countByTitle(@PathVariable String title) {
        long numberOfCopies = copyDbService.findByTitleAndCount(title);
        return numberOfCopies == 0 ? "Title: " + title + " Not Found" : ("Title: " + title
                + " has " + numberOfCopies + " copies");
    }

    @PostMapping(value = "/add/title{title}/author/{author}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<CopyDto> create(@PathVariable String title, @PathVariable String author) {
        Optional<Title> found = titleDbService.findTitleByTitleAndAuthor(title, author);
        Copy copy = CopyMapper.mapToCopy(new CopyDto(
           null,
                found.get(),
                StatusCopy.IN_CIRCULATION
        ));
        Copy toSaved = copyDbService.save(copy);
        return ResponseEntity.ok(CopyMapper.mapToCopyDto(toSaved));
    }

    @PutMapping(value = "/change/status/copyId/{copyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CopyDto> updateStatusCopy(@Valid @RequestBody CopyDto copyDto, @PathVariable Long copyId) {
        Optional<Copy> foundCopy = copyDbService.findCopyById(copyId);
        Copy setStatusCopy = CopyMapper.mapToCopy(new CopyDto(
                foundCopy.get().getCopyId(),
                foundCopy.get().getTitle(),
                copyDto.getStatus()
        ));
        return ResponseEntity.ok(CopyMapper.mapToCopyDto(copyDbService.save(setStatusCopy)));
    }
}