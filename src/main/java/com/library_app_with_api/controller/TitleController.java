package com.library_app_with_api.controller;

import com.library_app_with_api.controller.controllerService.RequiredByController;
import com.library_app_with_api.domain.Title;
import com.library_app_with_api.domain.dto.TitleDto;
import com.library_app_with_api.mapper.TitleMapper;
import com.library_app_with_api.service.TitleDbService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/v1/titles")
@RequiredArgsConstructor
public class TitleController implements RequiredByController <TitleDto> {

    @Autowired
    private TitleDbService titleDbService;

    @Override
    @GetMapping
    public List<TitleDto> findAll() {
        return TitleMapper.mapToTitleDtoList(titleDbService.findAll());
    }

    @GetMapping(value = "find/titleId/{TITLE_ID}")
    public Optional<Title> findTitleById(@PathVariable long TITLE_ID) {
        System.out.println("Title ID: " + TITLE_ID);
        return titleDbService.findTitleById(TITLE_ID);
    }

    @Override
    @PostMapping(value = "/create/title", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TitleDto> create(@Valid @RequestBody TitleDto objectDto) {
        Title title = TitleMapper.mapToTitle(new TitleDto(
                objectDto.getTitleId(),
                objectDto.getTitle(),
                objectDto.getAuthor(),
                objectDto.getDateRelease()

        ));
        Title toSaved = titleDbService.save(title);
        return ResponseEntity.ok(TitleMapper.mapToTitleDto(toSaved));
    }
}