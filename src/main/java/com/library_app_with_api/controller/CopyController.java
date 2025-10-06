package com.library_app_with_api.controller;

import com.library_app_with_api.domain.*;
import com.library_app_with_api.domain.dto.CopyDto;
import com.library_app_with_api.domain.enums.StatusCopy;
import com.library_app_with_api.mapper.CopyMapper;
import com.library_app_with_api.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/v1/copies")
@RequiredArgsConstructor
public class CopyController {

    private final CopyDbService copyDbService;

    private final TitleDbService titleDbService;

    @GetMapping
    public List<CopyDto> findAll() {
        return CopyMapper.mapToCopyDtoList(copyDbService.findAll());
    }

    @GetMapping(value = "/count/title/{title}/available")
    public String countByTitleAndAvailable(@PathVariable String title) {
        long numberOfCopies = copyDbService.findByTitleAndCount(title);
        return numberOfCopies == 0 ? "Title: " + title + " Not Found" : ("Title: " + title
                + " has " + numberOfCopies + " copies");
    }

    @GetMapping(value = "/find/copy/title/{title}/author/{author}")
    public List<CopyDto> findCopyByTitleAndAuthor(@PathVariable String title, @PathVariable String author) {
        return CopyMapper.mapToCopyDtoList(copyDbService.findCopyByTitleAndAuthor(title, author));
    }

    @PostMapping(value = "/add/title/{title}/author/{author}", consumes = MediaType.ALL_VALUE)
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