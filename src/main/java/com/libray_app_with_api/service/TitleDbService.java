package com.libray_app_with_api.service;

import com.libray_app_with_api.controller.errorController.exceptions.TitleNotFound;
import com.libray_app_with_api.domain.Copy;
import com.libray_app_with_api.domain.Title;
import com.libray_app_with_api.repository.TitleRepository;
import com.libray_app_with_api.service.serviceInterface.DbServicePattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TitleDbService implements DbServicePattern <Title> {

    private final TitleRepository titleRepository;

    @Override
    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    @Override
    public Title save(Title object) {
        return titleRepository.save(object);
    }

    public Optional<Title> findTitleById(long TITLE_ID) {
        return Optional.ofNullable(titleRepository.findById(TITLE_ID)
                .orElseThrow(TitleNotFound::new));
    }

    public Optional<Title> findTitleByTitleAndAuthor(String title, String author) {
        return Optional.ofNullable(titleRepository.findTitleByTitleAndAuthor(title, author)
                .orElseThrow(TitleNotFound::new));
    }
}