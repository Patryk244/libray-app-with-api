package com.libray_app_with_api.service;

import com.libray_app_with_api.domain.Title;
import com.libray_app_with_api.repository.TitleRepository;
import com.libray_app_with_api.service.serviceInterface.DbServicePattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}